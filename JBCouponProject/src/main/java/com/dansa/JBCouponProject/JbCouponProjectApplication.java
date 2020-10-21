package com.dansa.JBCouponProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JbCouponProjectApplication {

	public static void main(String[] args) {
				
		SpringApplication.run(JbCouponProjectApplication.class, args);
		System.out.println("IOC container was loaded");
		
	}

}
