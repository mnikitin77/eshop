package com.mvnikitin.eshop;

import com.mvnikitin.eshop.services.ProductService;
import com.mvnikitin.eshop.services.ProductServiceImpl;
import com.mvnikitin.eshop.services.ProductServicePaged;
import com.mvnikitin.eshop.services.ProductServicePagedImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class EshopClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopClientApplication.class, args);
	}
}
