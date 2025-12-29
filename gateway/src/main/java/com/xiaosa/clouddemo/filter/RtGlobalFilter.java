package com.xiaosa.clouddemo.filter;

import com.xiaosa.clouddemo.properties.DemoGatewayProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RtGlobalFilter implements GlobalFilter , Ordered {
    @Resource
    private DemoGatewayProperties demoGatewayProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(demoGatewayProperties.isRt()){
            ServerHttpRequest request = exchange.getRequest();
            long startTime = System.currentTimeMillis();
            String uti = request.getURI().toString();
            return chain.filter(exchange)
                    .doFinally((result) -> {
                        long endTime = System.currentTimeMillis();
                        log.info("Gateway RtGlobalFilter: Request URI:[{}],start:[{}],rt:[{}]", uti, startTime, endTime - startTime);
                    });
        }
        else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
