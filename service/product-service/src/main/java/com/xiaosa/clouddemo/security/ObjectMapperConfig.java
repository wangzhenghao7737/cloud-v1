package com.xiaosa.clouddemo.security;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * product
 */
@Configuration
public class ObjectMapperConfig {
    @Bean("securityObjectMapper")
    public ObjectMapper securityObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // --- 安全性：默认配置已足够安全 ---
        // 不启用 DefaultTyping 等危险功能，反序列化时需明确指定类型
        // 这在手动序列化/反序列化 (writeValueAsString/readValue) 时由开发者控制

        // --- 兼容性 ---
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // --- 可见性 (谨慎使用) ---
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // --- 日期时间 ---
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());

        // --- 健壮性 ---
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION); // <--- 使用 disable 替代 configure(false)

        mapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        mapper.disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
        return mapper;
    }
}
