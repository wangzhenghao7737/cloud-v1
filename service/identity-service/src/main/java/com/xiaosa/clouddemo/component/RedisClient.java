package com.xiaosa.clouddemo.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisClient {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource(name = "redisObjectMapper")
    private ObjectMapper objectMapper;

    // sms相关
    private final Long SMS_TIME = 60L * 1000L * 3L;
    private final TimeUnit SMS_TIME_UNIT = TimeUnit.MILLISECONDS;
    // token相关
    private final Long TOKEN_TIME = 60L * 1000L * 60L * 24L * 3L;
    private final TimeUnit TOKEN_TIME_UNIT = TimeUnit.MILLISECONDS;

    /**
     * 数据缓存
     */
    public <T> void set(String key, T value, Long time, TimeUnit timeUnit) {
        try {
            if(value == null || key == null || time == null || timeUnit == null){
                return;
            }
            String json = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, time, timeUnit);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize Object to json for key {}",key, e);
        }
    }
    public <T> T get(String key,Class<T> clazz) {
       try {
           String json = redisTemplate.opsForValue().get(key);
           if(ObjectUtils.isEmpty(json)){
               return null;
           }
           return objectMapper.readValue(json,clazz);
       } catch (Exception e) {
           log.error("Failed to deserialize object to object for key {}",key,e);
           return null;
       }
    }

    /**
     * token相关
     */
    public void setToken(String key, String value) {
        if(key == null || value == null){
            return;
        }
        redisTemplate.opsForValue().set(key,value,TOKEN_TIME,TOKEN_TIME_UNIT);
    }
    public String getToken(String key) {
        String json = redisTemplate.opsForValue().get(key);
        if(ObjectUtils.isEmpty(json)){
            return null;
        }
        return json;
    }

    /**
     * 验证码相关
     */
    private String getSmsKey(String phone){
        return "identity-service:login-sms:"+phone;
    }
    public void setSms(String phone, String code) {
        if(phone != null && code != null){
            redisTemplate.opsForValue().set(getSmsKey(phone), code, SMS_TIME, SMS_TIME_UNIT);
        }
    }
    public String getSms(String phone) {
        String json = redisTemplate.opsForValue().get(getSmsKey(phone));
        if(ObjectUtils.isEmpty(json)){
            return null;
        }
        return json;
    }
    public boolean checkSms(String phone, String code) {
        if(phone == null || code == null){
            return false;
        }
        String SmsCode = redisTemplate.opsForValue().get(getSmsKey(phone));
        if(StringUtils.hasText(SmsCode)){
            if(code.equals(SmsCode)){
                redisTemplate.delete(getSmsKey(phone));
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * redis键值相关
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
