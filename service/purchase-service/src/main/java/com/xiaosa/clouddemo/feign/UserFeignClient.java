package com.xiaosa.clouddemo.feign;

import com.xiaosa.clouddemo.dto.user.ReduceMoney;
import com.xiaosa.clouddemo.feign.fallback.UserFallbackFactory;
import com.xiaosa.clouddemo.result.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "/user", fallbackFactory = UserFallbackFactory.class)
public interface UserFeignClient {
    @PutMapping("/reduceMoney")
    ApiResponse<Boolean> reduceUserMoney(@Valid @RequestBody ReduceMoney reduceMoney);
}
