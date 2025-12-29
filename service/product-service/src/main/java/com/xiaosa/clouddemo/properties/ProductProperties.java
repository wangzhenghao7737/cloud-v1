package com.xiaosa.clouddemo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties(prefix = "product-config.cache")
public class ProductProperties {
    private boolean redis;

    public boolean getRedis() {
        return redis;
    }

    public void setRedis(boolean redis) {
        this.redis = redis;
    }
}
