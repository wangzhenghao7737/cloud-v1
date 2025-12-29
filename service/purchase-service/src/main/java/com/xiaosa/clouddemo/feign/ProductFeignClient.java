package com.xiaosa.clouddemo.feign;

import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.dto.product.ReduceStockDto;
import com.xiaosa.clouddemo.feign.fallback.ProductFallBackFactory;
import com.xiaosa.clouddemo.result.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", path = "/product",fallbackFactory = ProductFallBackFactory.class)
public interface ProductFeignClient {
    @GetMapping("/{id}")
    ApiResponse<ProductDto> getProductDtoById(@PathVariable("id") Long id);
    @PutMapping("/reduceStock")
    ApiResponse<Boolean> reduceStock(@Valid @RequestBody ReduceStockDto reduceStockDto);
}
