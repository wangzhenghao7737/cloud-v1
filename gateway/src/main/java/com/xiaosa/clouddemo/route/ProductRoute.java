package com.xiaosa.clouddemo.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRoute {
    @Bean
    public RouteLocator productRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-route",p->p
                        .path("/api/product/**")
                        .filters(f -> f.rewritePath("/api/product/(?<segment>.*)", "/product/${segment}"))
                        .uri("lb://product-service"))
                .build();
    }
}
