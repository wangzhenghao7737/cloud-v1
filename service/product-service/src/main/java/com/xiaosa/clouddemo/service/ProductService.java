package com.xiaosa.clouddemo.service;

import com.xiaosa.clouddemo.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.dto.product.ReduceStockDto;
import com.xiaosa.clouddemo.entity.ProductCommon;

import java.util.List;

/**
* @author D14
* @description 针对表【product(商品信息表 (无物理外键))】的数据库操作Service
* @createDate 2025-12-18 22:06:00
*/
public interface ProductService extends IService<Product> {
    ProductDto getProductDtoById(Long id);
    ProductDto getProductDtoByIdFromRedis(Long id);
    List<ProductDto> getProductDtoListWithRedis(List<Long> ids);
    ProductCommon getProductCommonById(Long id);
    boolean reduceStock(ReduceStockDto reduceStockDto);
}
