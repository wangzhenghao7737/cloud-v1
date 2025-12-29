package com.xiaosa.clouddemo.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseRoute {
    @Bean
    public RouteLocator purchaseRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("purchase-route",p->p
                        .path("/api/purchase/**")
                        .filters(f -> f.rewritePath("/api/purchase/(?<segment>.*)", "/purchase/${segment}"))
                        .uri("lb://purchase-service")
                ).build();
    }
}
