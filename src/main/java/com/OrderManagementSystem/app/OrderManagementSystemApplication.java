package com.OrderManagementSystem.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderManagementSystemApplication {

	public static void main(String[] args) {
        SpringApplication.run(OrderManagementSystemApplication.class, args);
        System.out.println("Application started successfully!");
        System.out.println("Test endpoint: http://localhost:8080/test-contracts");
        System.out.println("Test endpoint: http://localhost:8080/test-contract-types");
        System.out.println("Test endpoint: http://localhost:8080/test-customers");
        System.out.println("Test endpoint: http://localhost:8080/test-orders");
        System.out.println("Test endpoint: http://localhost:8080/test-products");
        System.out.println("Test endpoint: http://localhost:8080/test-services");
        System.out.println("Test endpoint: http://localhost:8080/test-units");
	}

}
