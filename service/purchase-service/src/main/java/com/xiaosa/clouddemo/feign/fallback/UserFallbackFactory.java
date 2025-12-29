package com.xiaosa.clouddemo.feign.fallback;

import com.xiaosa.clouddemo.dto.user.ReduceMoney;
import com.xiaosa.clouddemo.entity.UserCommon;
import com.xiaosa.clouddemo.feign.UserFeignClient;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.FeignFallbackHelper;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public ApiResponse<Boolean> reduceUserMoney(ReduceMoney reduceMoney) {
                return FeignFallbackHelper.fallbackResult("Purchase:user-service feign failed", cause);
            }
        };
    }
}
