package com.xiaosa.clouddemo.feign.fallback;

import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.dto.product.ReduceStockDto;
import com.xiaosa.clouddemo.feign.ProductFeignClient;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.FeignFallbackHelper;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductFallBackFactory implements FallbackFactory<ProductFeignClient> {
    @Override
    public ProductFeignClient create(Throwable cause) {
        return new ProductFeignClient() {
            @Override
            public ApiResponse<ProductDto> getProductDtoById(Long id) {
                return FeignFallbackHelper.fallbackResult("Purchase:product-service feign:getProductDtoById failed", cause);
            }

            @Override
            public ApiResponse<Boolean> reduceStock(ReduceStockDto reduceStockDto) {
                return FeignFallbackHelper.fallbackResult("Purchase:product-service feign:reduceStock failed", cause);
            }
        };
    }
}
