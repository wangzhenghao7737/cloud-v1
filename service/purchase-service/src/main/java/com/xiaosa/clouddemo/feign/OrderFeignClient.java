package com.xiaosa.clouddemo.feign;

import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.feign.fallback.OrderFallbackFactory;
import com.xiaosa.clouddemo.result.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", path = "/order", fallbackFactory = OrderFallbackFactory.class)
public interface OrderFeignClient {
    @PostMapping("/create")
    ApiResponse<Boolean> createOrder(@Valid @RequestBody OrderCreateDto order);
}
