package com.upgradeinc.extremecamp.campsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExtremeCampCampsiteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtremeCampCampsiteServiceApplication.class, args);
	}

}
