package com.notix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class ZipkinTracingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZipkinTracingApplication.class, args);
	}
}
