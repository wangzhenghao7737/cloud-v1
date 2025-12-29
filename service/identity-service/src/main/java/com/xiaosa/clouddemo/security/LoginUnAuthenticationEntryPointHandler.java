package com.xiaosa.clouddemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaosa.clouddemo.codeEnum.IdentityCodeEnum;
import com.xiaosa.clouddemo.result.ApiResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * 认证失败处理类
 * 未登录时访问私有化资源会调用此方法
 */
@Slf4j
@Component
public class LoginUnAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ApiResponse<Object> error = ApiResponse.fail(IdentityCodeEnum.LOGIN_UN_AUTHENTICATION);
        String json = objectMapper.writeValueAsString(error);
        response.getWriter().print(json);
    }
}
