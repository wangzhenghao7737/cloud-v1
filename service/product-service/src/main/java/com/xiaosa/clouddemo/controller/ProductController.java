package com.xiaosa.clouddemo.controller;

import com.xiaosa.clouddemo.codeEnum.ProductCodeEnum;
import com.xiaosa.clouddemo.codeEnum.SystemCodeEnum;
import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.dto.product.ProductDtoList;
import com.xiaosa.clouddemo.dto.product.ReduceStockDto;
import com.xiaosa.clouddemo.entity.ProductCommon;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.properties.ProductProperties;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;
    @Resource
    private ProductProperties productProperties;

    @GetMapping("/{id}")
    public ApiResponse<ProductDto> getProductDtoById(@PathVariable("id") Long id) {
        if(id <=0){
            throw new BusinessException(SystemCodeEnum.SYSTEM_REQUEST_PARAMETER_ERROR);
        }
        ProductDto productDto;
        if(productProperties.getRedis()){
            productDto = productService.getProductDtoByIdFromRedis(id);
        }else {
            productDto = productService.getProductDtoById(id);
        }
        return ApiResponse.success(productDto);
    }
    @PostMapping("/list")
    public ApiResponse<List<ProductDto>> getProductDtoList(@Valid @RequestBody ProductDtoList list) {
        if(CollectionUtils.isEmpty(list.getIds())){
            return ApiResponse.success(List.of());
        }
        return ApiResponse.success(productService.getProductDtoListWithRedis(list.getIds()));
    }
    @GetMapping("/detail/{id}")
    public ApiResponse<ProductCommon> getProductCommonByIdDetail(@PathVariable("id") Long id) {
        if(id<=0){
            throw new BusinessException(ProductCodeEnum.PRODUCT_REQUEST_PARAMETER_ERROR);
        }
        ProductCommon product = productService.getProductCommonById(id);
        return ApiResponse.success(product);
    }

    // todo 一次SQL: update product set stock=stock-num where pid=xxx and stock > num
    @PutMapping("/reduceStock")
    public ApiResponse<Boolean> reduceStock(@Valid @RequestBody ReduceStockDto reduceStockDto) {
        return ApiResponse.success(productService.reduceStock(reduceStockDto));
    }
}
