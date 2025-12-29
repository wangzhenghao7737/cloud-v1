package com.xiaosa.clouddemo.feign.fallback;

import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.feign.OrderFeignClient;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.FeignFallbackHelper;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderFallbackFactory implements FallbackFactory<OrderFeignClient> {
    @Override
    public OrderFeignClient create(Throwable cause) {
        return new OrderFeignClient() {
            @Override
            public ApiResponse<Boolean> createOrder(OrderCreateDto order) {
                return FeignFallbackHelper.fallbackResult("Purchase:order-service feign failed", cause);
            }
        };
    }
}
