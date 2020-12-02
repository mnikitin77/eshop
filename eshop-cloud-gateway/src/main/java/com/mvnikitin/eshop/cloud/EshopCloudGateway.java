package com.mvnikitin.eshop.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EshopCloudGateway {
    public static void main(String[] args) {
        SpringApplication.run(EshopCloudGateway.class, args);
    }
}
