package com.nipppon.p2p.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// below imports are written for server configuration
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //this is for enabling schedulers
public class ProcurementManagementSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args){
		SpringApplication.run(ProcurementManagementSystemApplication.class, args);
	}

// added for server configuration
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// Customize the application builder if needed
		return builder.sources(ProcurementManagementSystemApplication.class);
	}
}