package com.xiaosa.clouddemo.service.impl;

import com.xiaosa.clouddemo.codeEnum.ProductCodeEnum;
import com.xiaosa.clouddemo.codeEnum.UserCodeEnum;
import com.xiaosa.clouddemo.constant.CommonConstant;
import com.xiaosa.clouddemo.codeEnum.OrderCodeEnum;
import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.dto.product.ReduceStockDto;
import com.xiaosa.clouddemo.dto.purchase.PurChaseDto;
import com.xiaosa.clouddemo.dto.user.ReduceMoney;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.feign.OrderFeignClient;
import com.xiaosa.clouddemo.feign.ProductFeignClient;
import com.xiaosa.clouddemo.feign.UserFeignClient;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.PurchaseService;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    @Resource
    private ProductFeignClient productFeignClient;
    @Resource
    private OrderFeignClient orderFeignClient;
    @Resource
    private UserFeignClient userFeignClient;
    /**
     * 创建订单
     *
     */
    @GlobalTransactional
    @Override
    public PurChaseDto createPurchase(PurChaseDto purChaseDto) {
        // 获取商品信息 todo redis
        log.info("getProductDtoById,{}", purChaseDto.getProductId());
        final ApiResponse<ProductDto> productDtoById = productFeignClient.getProductDtoById(purChaseDto.getProductId());
        if(productDtoById.getCode()!= CommonConstant.SUCCESS_CODE){
            throw new BusinessException(productDtoById.getCode(), productDtoById.getMessage(), productDtoById.getDescription());
        }
        // 查询到的商品信息
        final ProductDto productDto = productDtoById.getData();
        // 创建订单
        OrderCreateDto orderCreateDto = new OrderCreateDto();
        orderCreateDto.setUserId(purChaseDto.getUserId());
        orderCreateDto.setProductId(purChaseDto.getProductId());
        orderCreateDto.setQuantity(purChaseDto.getCount());
        orderCreateDto.setTotalPrice(productDto.getPrice().multiply(new BigDecimal(purChaseDto.getCount())));
        ApiResponse<Boolean> order = orderFeignClient.createOrder(orderCreateDto);
        if(order.getCode()!= CommonConstant.SUCCESS_CODE){
            throw new BusinessException(order.getCode(), order.getMessage(), order.getDescription());
        }
        // 减用户余额
        ReduceMoney reduceMoney = new ReduceMoney();
        reduceMoney.setUserId(purChaseDto.getUserId());
        reduceMoney.setMoney(productDto.getPrice().multiply(new BigDecimal(purChaseDto.getCount())));
        final ApiResponse<Boolean> booleanApiResponse = userFeignClient.reduceUserMoney(reduceMoney);
        if(booleanApiResponse.getCode()!= CommonConstant.SUCCESS_CODE){
            throw new BusinessException(booleanApiResponse.getCode(), booleanApiResponse.getMessage(), booleanApiResponse.getDescription());
        }
        // 减少库存
        if(productDto.getStock()<purChaseDto.getCount()){
            throw new BusinessException(ProductCodeEnum.PRODUCT_NOT_ENOUGH);
        }
        ReduceStockDto reduceStockDto = new ReduceStockDto();
        reduceStockDto.setProductId(purChaseDto.getProductId());
        reduceStockDto.setCount(purChaseDto.getCount());
        final ApiResponse<Boolean> reduceResult = productFeignClient.reduceStock(reduceStockDto);
        if(reduceResult.getCode()!= CommonConstant.SUCCESS_CODE){
            throw new BusinessException(reduceResult.getCode(), reduceResult.getMessage(), reduceResult.getDescription());
        }
        purChaseDto.setResult(true);
        return purChaseDto;
    }
}
