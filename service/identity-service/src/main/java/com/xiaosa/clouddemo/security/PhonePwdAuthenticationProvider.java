package com.xiaosa.clouddemo.security;

import com.xiaosa.clouddemo.entity.domain.User;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class PhonePwdAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        String phone = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        // 从数据库根据手机号和密码查询用户
        User user= userDetailsService.loadUserByPhone(phone);
        // 判断密码是否正确
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("账号或密码错误");
        }
        //授权
        UserDetails userDetails = userDetailsService.buildLoginUserDetails(user);
        return new PhonePwdAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhonePwdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
