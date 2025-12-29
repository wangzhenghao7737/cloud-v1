package com.xiaosa.clouddemo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "my-config.log")
public class DemoGatewayProperties {

    private boolean rt;

    private int time;

    @Override
    public String toString() {
        return "DemoGatewayProperties{" +
                "rt=" + rt +
                ", time=" + time +
                '}';
    }
}

