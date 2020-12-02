package com.mvnikitin.eshop.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class EshopCloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(EshopCloudConfigApplication.class, args);
    }
}
