package com.xiaosa.clouddemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaosa.clouddemo.codeEnum.IdentityCodeEnum;
import com.xiaosa.clouddemo.result.ApiResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginUnAccessDeniedHandler implements AccessDeniedHandler {
    /**
     *已经登录，但是没有权限
     */
    @Resource(name="redisObjectMapper")
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ApiResponse<Object> fail = ApiResponse.fail(IdentityCodeEnum.AUTHORIZATION_NOT_PERMIT);
        //将消息json化
        String json = objectMapper.writeValueAsString(fail);
        //送到客户端
        response.getWriter().print(json);
    }
}
