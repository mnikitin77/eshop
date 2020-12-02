package com.mvnikitin.eshop.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EshopCloudDiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(EshopCloudDiscoveryApplication.class, args);
    }
}
