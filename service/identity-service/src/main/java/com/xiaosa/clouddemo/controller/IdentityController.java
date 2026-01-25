package com.xiaosa.clouddemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaosa.clouddemo.codeEnum.IdentityCodeEnum;
import com.xiaosa.clouddemo.codeEnum.SystemCodeEnum;
import com.xiaosa.clouddemo.component.RedisClient;
import com.xiaosa.clouddemo.entity.domain.User;
import com.xiaosa.clouddemo.entity.dto.AccountDto;
import com.xiaosa.clouddemo.entity.dto.PhoneDto;
import com.xiaosa.clouddemo.entity.dto.SmsDto;
import com.xiaosa.clouddemo.entity.dto.UserSecurityDto;
import com.xiaosa.clouddemo.entity.dto.group.PhoneGroup;
import com.xiaosa.clouddemo.entity.dto.group.SmsGroup;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.security.LoginUserDetails;
import com.xiaosa.clouddemo.security.PhonePwdAuthenticationToken;
import com.xiaosa.clouddemo.security.SmsCodeAuthenticationToken;
import com.xiaosa.clouddemo.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/identity")
public class IdentityController {
    private static final SecureRandom secureRandom = new SecureRandom();
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisClient redisClient;

    @Resource(name = "redisObjectMapper")
    private ObjectMapper objectMapper;

    @PostMapping("/account")
    public ApiResponse<Map<String, String>> loginByAccount(@Validated @RequestBody AccountDto accountDto   ) {
        Authentication auth = new UsernamePasswordAuthenticationToken(accountDto.getAccount(), accountDto.getPassword());
        return performLogin(auth);
    }
    @PostMapping("/phone")
    public ApiResponse<Map<String, String>> loginByPhoneAndPassword(@Validated @RequestBody PhoneDto phoneDto) {
        Authentication auth = new PhonePwdAuthenticationToken(phoneDto.getPhone(), phoneDto.getPassword());
        return performLogin(auth);
    }
    @PostMapping("/sms")
    public ApiResponse<Map<String, String>> loginBySms(@Validated({PhoneGroup.class}) @RequestBody SmsDto smsDto) {
        Authentication auth = new SmsCodeAuthenticationToken(smsDto.getPhone(), smsDto.getCode());
        return performLogin(auth);
    }
    @PostMapping("/refreshLogin")
    public ApiResponse<Map<String, String>> refreshLogin(HttpServletRequest request) {
        //判断曾经登录是否还在有效期
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            return ApiResponse.fail(IdentityCodeEnum.LOGIN_TOKEN_MISSING);
        }
        if (!JwtUtils.verify(token)) {
            return ApiResponse.fail(IdentityCodeEnum.LOGIN_TOKEN_EXPIRED);
        }
        String userId = JwtUtils.getSubject(token);
        String s = redisClient.getToken(JwtUtils.getLoginTokenKey(userId));
        if (!StringUtils.hasText(s)) {
            return ApiResponse.fail(IdentityCodeEnum.LOGIN_TOKEN_EXPIRED);
        }
//        redisClient.expire(JwtUtils.getLoginTokenKey(userId),  LoginConstant.TOKEN_EXPIRE_MILLIS);
        //LoginUserDetails principal = objectMapper.readValue(s, LoginUserDetails.class);
        String new_token = JwtUtils.sign(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", new_token);
        return ApiResponse.success(map);
    }
    //todo 重复发送逻辑
    @PutMapping("/sendSms")
    public ApiResponse<String> sendSms(@Validated({SmsGroup.class}) @RequestBody SmsDto smsDto) {
        int code = secureRandom.nextInt(10000);
        redisClient.setSms(smsDto.getPhone(), String.format("%04d", code));
        return ApiResponse.success(String.format("%04d", code));
    }
    /**
     * 登出
     * 项目不使用cookie和session，是无状态，可以自定义logout接口
     */
    @DeleteMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            return ApiResponse.fail(IdentityCodeEnum.LOGIN_TOKEN_MISSING);
        }
        redisClient.delete(JwtUtils.getLoginTokenKey(JwtUtils.getSubject(token)));
        return ApiResponse.success("退出成功");
    }
    private ApiResponse<Map<String, String>> performLogin(Authentication authentication) {
        try {
            Authentication authenticated = authenticationManager.authenticate(authentication);
            LoginUserDetails principal = (LoginUserDetails) authenticated.getPrincipal();
            UserSecurityDto user = principal.getUser();
            // 生成新 token
            String token = JwtUtils.sign(String.valueOf(user.getUserId()));
            String json = objectMapper.writeValueAsString(principal);
            redisClient.setToken(JwtUtils.getLoginTokenKey(user.getUserId()), json);

            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return ApiResponse.success(map);
        } catch (JsonProcessingException e) {
            return ApiResponse.fail(IdentityCodeEnum.JSON_PROCESSING_ERROR);
        }catch(AuthenticationException e){
            throw e;
        } catch (Exception e) {
            return ApiResponse.fail(SystemCodeEnum.SYSTEM_ERROR); // 可根据异常类型细化
        }
    }

    private boolean isValidPhone(String phone) {
        return StringUtils.hasText(phone) && phone.matches("1[3-9]\\d{9}");
    }
}
