package com.xiaosa.clouddemo.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRoute {
    @Bean
    public RouteLocator userRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-route",p->p
                        .path("/api/user/**")
                        .and()
                        .header("Authorization", "Bearer .+")
                        .filters(f->f.rewritePath("/api/user/(?<segment>.*)", "/user/${segment}"))
                        .uri("lb://user-service"))
                .build();
    }
}
