package com.xiaosa.clouddemo.security;

import com.xiaosa.clouddemo.component.RedisClient;
import com.xiaosa.clouddemo.entity.domain.User;
import com.xiaosa.clouddemo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private RedisClient redisClient;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDetailsServiceImpl userDetailsService; // 复用加载逻辑

    @Override
    public Authentication authenticate(Authentication authentication) {
        if (!supports(authentication.getClass())) {
            return null;
        }

        String phone = (String) authentication.getPrincipal();
        String inputCode = (String) authentication.getCredentials();

        if (!StringUtils.hasText(phone) || !StringUtils.hasText(inputCode)) {
            throw new BadCredentialsException("手机号或验证码为空");
        }

        // 1. 从 Redis 获取正确的验证码（假设存的是 "sms:code:{phone}"）
        String correctCode = redisClient.getSms(phone);
        if (!StringUtils.hasText(correctCode) || !correctCode.equals(inputCode)) {
            throw new BadCredentialsException("验证码错误或已过期");
        }

        // 3. 构建 UserDetails（复用你原有的逻辑）
        User user = userDetailsService.loadUserByPhone(phone);
        UserDetails userDetails = userDetailsService.buildLoginUserDetails(user);

        // 4. 用户状态验证
        if (!userDetails.isEnabled()) {
            throw new BadCredentialsException("用户状态异常");
        }

        // 5. 返回已认证的 Token
        return new SmsCodeAuthenticationToken(userDetails, inputCode, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
