package com.xiaosa.clouddemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaosa.clouddemo.component.RedisClient;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
/**
 * 登出处理
 * 参数：用户的jwt
 * 描述：从jwt中解析subject（用户id），根据id删除redis中的token
 * 效果：只要jwt能正常解析，使用旧的jwt使用POST logout后，新的jwt也会被删除
 */
@Component
public class LogoutStatusSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private RedisClient redisClient;
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        String token = request.getHeader("token");
        //判断token是否存在,并从redis中删除
        ApiResponse result =null;
        if (!StringUtils.hasText(token)){
         result = ApiResponse.success("注销失败，无jwt");
        }
        redisClient.delete(JwtUtils.getLoginTokenKey(JwtUtils.getSubject( token)));
        //返回给客户端注销成功的提示
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        result = ApiResponse.success("注销成功logout");
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);
    }
}
