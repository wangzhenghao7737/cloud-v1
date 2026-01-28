package com.xiaosa.clouddemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaosa.clouddemo.component.RedisClient;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource(name ="securityObjectMapper")
    private ObjectMapper objectMapper;
    @Resource
    private RedisClient redisClient;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("token");

        if (StringUtils.hasText(token)) {
            try {
                // 验证 token 并获取用户信息
                if (JwtUtils.verify(token)) { // ← 先验证签名和过期
                    String subject = JwtUtils.getSubject(token);
                    String key = JwtUtils.getLoginTokenKey(subject);
                    String json = redisClient.getToken(key);
                    if (StringUtils.hasText(json)) {
                        LoginUserDetails userDetails = objectMapper.readValue(json, LoginUserDetails.class);
                        if (userDetails != null) {
                            UsernamePasswordAuthenticationToken auth =
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    }
                }
                // 如果 verify 失败或 Redis 无数据，当作无效 token 处理（不设置 Authentication）
            } catch (Exception e) {
                // 记录日志，但不中断流程
                log.warn("Invalid or expired JWT token: {}", token, e);
            }
        }
        // 无论是否有 token，都继续放行，交给 Spring Security 授权机制判断
        filterChain.doFilter(request, response);
    }
}
