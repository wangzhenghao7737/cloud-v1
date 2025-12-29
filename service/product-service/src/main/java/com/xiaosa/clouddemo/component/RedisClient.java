package com.xiaosa.clouddemo.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaosa.clouddemo.constant.ProductConstant;
import com.xiaosa.clouddemo.utils.KeyUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class RedisClient {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource(name = "redisObjectMapper")
    private ObjectMapper objectMapper;

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
    public <T> void setNull(String key) {
        if(key == null){
            return;
        }
        redisTemplate.opsForValue().set(key
                ,ProductConstant.PRODUCT_NOT_EXIST
        ,ProductConstant.REDIS_NULL_VALUE_TTL_SECONDS
        ,ProductConstant.REDIS_TIME_UNIT);
    }

    public Boolean lock(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key,value,ProductConstant.REDIS_LOCK_TIME, ProductConstant.REDIS_TIME_UNIT);
    }

    public <T> T get(String key,Class<T> clazz) {
       try {
           String json = redisTemplate.opsForValue().get(key);
           if(ObjectUtils.isEmpty(json)||ProductConstant.PRODUCT_NOT_EXIST.equals(json)){
               return null;
           }
           return objectMapper.readValue(json,clazz);
       } catch (Exception e) {
           log.error("Failed to deserialize object to object for key {}",key,e);
           return null;
       }
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    public Long execute(RedisScript<Long> script, List<String> key, String value){
        return redisTemplate.execute(script, key, value);
    }
    public <T> Map<Long,T> getProductByIdsInBatch(List<Long> ids, Class<T> clazz) {
        if(ids == null || ids.isEmpty()){
            return Collections.emptyMap();
        }
        // 1 获取key列表
        List<String> keys = ids.stream()
                .filter(Objects::nonNull)
                .map(KeyUtils::getProductKey)
                .toList();
        //2 批量get
        // todo 默认keys没有空值
        List<String> jsonValues = redisTemplate.opsForValue().multiGet(keys);
        // 3 解析
        Map<Long,T> resultMap = new HashMap<>();
        for(int i = 0; i < keys.size(); i++){
            Long id = ids.get(i);
            String json = jsonValues.get(i);
            if(json==null || ProductConstant.PRODUCT_NOT_EXIST.equals(json)){
                continue;
            }
            try {
                T t = objectMapper.readValue(json, clazz);
                resultMap.put(id,t);
            }catch (Exception e){
                log.error("Failed to deserialize product JSON for id: {}", id, e);
            }
        }
        return resultMap;
    }
}
