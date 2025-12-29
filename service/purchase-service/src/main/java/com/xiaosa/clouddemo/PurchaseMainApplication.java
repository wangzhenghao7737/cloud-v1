package com.xiaosa.clouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xiaosa.clouddemo.feign")
@EnableTransactionManagement
public class PurchaseMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchaseMainApplication.class, args);
    }
}
