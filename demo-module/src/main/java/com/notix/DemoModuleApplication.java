package com.notix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoModuleApplication.class, args);
	}
}
