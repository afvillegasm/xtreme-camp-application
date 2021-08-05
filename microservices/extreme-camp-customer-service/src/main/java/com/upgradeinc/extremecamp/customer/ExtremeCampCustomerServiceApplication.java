package com.upgradeinc.extremecamp.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExtremeCampCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtremeCampCustomerServiceApplication.class, args);
	}

}
