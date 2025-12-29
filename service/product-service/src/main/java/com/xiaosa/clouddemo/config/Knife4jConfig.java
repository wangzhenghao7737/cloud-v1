package com.xiaosa.clouddemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("我的微服务 API 文档")
                        .version("1.0.0")
                        .description("欢迎使用我们的 API！这里是详细的接口说明。")
                        .contact(new Contact()
                                .name("开发团队")
                                .email("support@example.com"))
                        .license(new License()
                                .name("Apache License, Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
        // 你可以在这里继续添加 Servers, Components 等其他全局配置
    }
    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default") // 分组名称，对应 Knife4j UI 左侧的分组
                .packagesToScan("com.xiaosa.clouddemo.controller") // 指定要扫描的包
                // .pathsToMatch("/api/**") // 可选：指定要匹配的路径前缀
                // .pathsToExclude("/internal/**") // 可选：排除特定路径
                // .packagesToExclude("com.example.yourproject.internal") // 可选：排除特定包
                .build();
    }
}
