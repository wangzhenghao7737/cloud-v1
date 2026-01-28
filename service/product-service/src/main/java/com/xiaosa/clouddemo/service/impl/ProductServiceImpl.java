package com.xiaosa.clouddemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaosa.clouddemo.codeEnum.SystemCodeEnum;
import com.xiaosa.clouddemo.component.RedisClient;
import com.xiaosa.clouddemo.codeEnum.ProductCodeEnum;
import com.xiaosa.clouddemo.constant.ProductConstant;
import com.xiaosa.clouddemo.domain.Product;
import com.xiaosa.clouddemo.domain.ProductCreate;
import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.dto.product.ReduceStockDto;
import com.xiaosa.clouddemo.entity.ProductCommon;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.properties.ProductProperties;
import com.xiaosa.clouddemo.service.ProductDtoMapper;
import com.xiaosa.clouddemo.service.ProductService;
import com.xiaosa.clouddemo.mapper.ProductMapper;
import com.xiaosa.clouddemo.utils.KeyUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author D14
* @description 针对表【product(商品信息表 (无物理外键))】的数据库操作Service实现
* @createDate 2025-12-18 22:06:00
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductDtoMapper productDtoMapper;

    @Resource
    private RedisClient redisClient;

    @Resource
    private ProductProperties productProperties;

    // Lua 脚本用于原子性地释放锁 (只有持有锁的 clientId 才能删除)
    private static final String UNLOCK_LUA_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "    return redis.call('del', KEYS[1]) " +
                    "else " +
                    "    return 0 " +
                    "end";
    private static final RedisScript<Long> UNLOCK_SCRIPT = new DefaultRedisScript<>(UNLOCK_LUA_SCRIPT, Long.class);

    /**
     * 直接查询数据库，使用redis实现分布式锁
     */
    @Override
    public ProductDto getProductDtoById(Long id) {
        Product product = productMapper.selectById(id);
        if (ObjectUtils.isEmpty(product)) {
            throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_FOUND);
        }
        return productDtoMapper.toDto(product);
    }

    @Override
    public ProductDto getProductDtoByIdFromRedis(Long id) {
        String key = KeyUtils.getProductKey(id);
        ProductDto productDto = redisClient.get(key, ProductDto.class);;
        // 缓存命中
        if (redisClient.hasKey(key)) {
            // 数据库不存在，极短时间过期
            if(ObjectUtils.isEmpty(productDto)){
                throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_FOUND);
            }
            return productDto;
        }
        // 缓存未命中
        String lockKey = KeyUtils.getLockKey(id);
        String lockValue = KeyUtils.getLockValue();
        try {
            Boolean lockAcquired = redisClient.lock(lockKey, lockValue);
            //获得锁
            if (Boolean.TRUE.equals(lockAcquired)) {
                //二次检查
                productDto = redisClient.get(key, ProductDto.class);
                if(productDto != null){
                    return productDto;
                }
                // --- 获取锁成功 ---
                try {
                    Product product = productMapper.selectById(id);
                    if (ObjectUtils.isEmpty(product)) {
                        redisClient.setNull(key);
                        throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_FOUND);
                    }
                    productDto = productDtoMapper.toDto(product);
                    redisClient.set(key,productDto,ProductConstant.REDIS_SECONDS,ProductConstant.REDIS_TIME_UNIT);
                    return productDto;
                }
                finally {
                    // --- 【关键】释放锁 ---
                    // 使用 execute 方法运行 Lua 脚本
                    // 参数:
                    // 1. script: 要执行的 RedisScript 对象
                    // 2. keys: Lua 脚本中的 KEYS 列表 (这里是锁的 key)
                    // 3. args: Lua 脚本中的 ARGV 列表 (这里是锁的 value)
                    redisClient.execute(
                            UNLOCK_SCRIPT,
                            Collections.singletonList(lockKey), // KEYS[1]
                            lockValue                           // ARGV[1]
                    );
                }
            }
            else {
                for(int i = ProductConstant.RETRY_TIMES;i>=0;i--){
                    Thread.sleep(ProductConstant.WAIT_TIME);
                    productDto = redisClient.get(key, ProductDto.class);
                    if(productDto != null){
                        return productDto;
                    }
                }
                throw new BusinessException(SystemCodeEnum.SYSTEM_BUSY);
            }
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            log.error("getProductDtoByIdFromRedis error", e);
            throw new BusinessException(SystemCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public List<ProductDto> getProductDtoListWithRedis(List<Long> ids) {
        // 允许则先从redis查找商品 实际商品顺序与请求参数顺序不一定一致
        if(productProperties.getRedis()){
            Map<Long, ProductDto> redisProductDtoMap = redisClient.getProductByIdsInBatch(ids, ProductDto.class);
            // 获取缓存未查到的ids
            ids = ids.stream()
               .filter(id->!redisProductDtoMap.containsKey(id))
                    .toList();
            //数据库查询出的product list
            List<Product> products = productMapper.selectByIds(ids);
            Map<Long, ProductDto> DBCollect = products.stream()
                    .filter(Objects::nonNull)
                    .map(product -> productDtoMapper.toDto(product))
                    .collect(Collectors.toMap(ProductDto::getId, Function.identity()));
            // 合并redis和DB的map
            Map<Long, ProductDto> fullDto = new HashMap<>(redisProductDtoMap);
            fullDto.putAll(DBCollect);
            return ids.stream()
                    .map(fullDto::get)
                    .filter(Objects::nonNull)
                    .toList();
        }else {
            //数据库查询
            List<Product> products = productMapper.selectByIds(ids);
            return products.stream()
                    .filter(Objects::nonNull)
                    .map(product -> productDtoMapper.toDto(product))
                    .toList();
        }
    }

    @Override
    public ProductCommon getProductCommonById (Long id){
        Product product = productMapper.selectById(id);
        if (ObjectUtils.isEmpty(product)) {
            throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_FOUND);
        }
        return productDtoMapper.toCommon(product);
    }
    /**
         * 减库存
         * 方案1：先获取商品，再判断，再减库存
         *  需要事务和并发处理（FOR UPDATE）
         * 方案2：mybatis-plus的UpdateWrapper
         *  无法详细判断具体错误
         * 方案3：结合1，2
         */
    @Transactional
    @Override
    public boolean reduceStock (ReduceStockDto reduceStockDto){
            if (ObjectUtils.isEmpty(reduceStockDto)
                    || reduceStockDto.getCount() <= 0
                    || reduceStockDto.getProductId() <= 0) {
                throw new BusinessException(ProductCodeEnum.PRODUCT_REQUEST_PARAMETER_ERROR);
            }
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", reduceStockDto.getProductId());
            queryWrapper.eq("is_deleted", ProductConstant.UNDELETED);
            queryWrapper.select("id", "stock", "status");
            Product product = productMapper.selectOne(queryWrapper);
            if (ObjectUtils.isEmpty(product)) {
                throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_FOUND);
            }
            if (product.getStatus() != 1) {
                throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_START_SALE);
            }
            if (product.getStock() < reduceStockDto.getCount()) {
                throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_ENOUGH);
            }
            UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", reduceStockDto.getProductId());
            updateWrapper.ge("stock", reduceStockDto.getCount());
            updateWrapper.setSql("stock = stock - " + reduceStockDto.getCount());
            final int update = productMapper.update(null, updateWrapper);
            if (update != ProductConstant.UPDATE_SUCCESS) {
                throw new BusinessException(ProductCodeEnum.PRODUCT_UPDATE_ERROR);
            }
            return true;
        }

    @Override
    public boolean insertProduct(ProductCreate productCreate) {
        Product entity = productDtoMapper.toEntity(productCreate);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String)authentication.getPrincipal();
        entity.setCreatedBy(Long.valueOf(userId));
        int insert = productMapper.insert(entity);
        return insert == ProductConstant.INSERT_SUCCESS;
    }
}



