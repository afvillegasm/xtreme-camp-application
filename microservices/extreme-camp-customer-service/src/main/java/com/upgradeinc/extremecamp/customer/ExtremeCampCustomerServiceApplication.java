package com.upgradeinc.extremecamp.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableEurekaClient
public class ExtremeCampCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtremeCampCustomerServiceApplication.class, args);
	}

}
