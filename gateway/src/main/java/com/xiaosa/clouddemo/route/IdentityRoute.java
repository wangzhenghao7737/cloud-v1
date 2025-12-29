package com.xiaosa.clouddemo.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityRoute {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("identity-routes",p->p
                .path("/api/identity/**")
                .filters(f -> f.rewritePath("/api/identity/(?<segment>.*)", "/identity/${segment}"))
                .uri("lb://identity-service"))
        .build();
    }
}
