package com.xiaosa.clouddemo.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRoute {
    @Bean
    public RouteLocator orderRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order-routes",p->p
                        .path("/api/order/**")
                        .filters(f -> f.rewritePath("/api/order/(?<segment>.*)", "/order/${segment}"))
                        .uri("lb://order-service"))
                .build();
    }
}
