package com.notix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
        
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

	@Bean
	public AlwaysSampler defaultSampler() {

		return new AlwaysSampler();
	}

}
