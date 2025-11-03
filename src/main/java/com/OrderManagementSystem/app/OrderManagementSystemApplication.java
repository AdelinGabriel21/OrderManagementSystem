package com.OrderManagementSystem.app;

import com.OrderManagementSystem.app.controller.ContractController;
import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.ContractRepository;
import com.OrderManagementSystem.app.repository.InMemoryRepo;
import com.OrderManagementSystem.app.service.ContractService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class OrderManagementSystemApplication {

	public static void main(String[] args) {
        SpringApplication.run(OrderManagementSystemApplication.class, args);


        ContractRepository repository = new ContractRepository();
        ContractService contractService = new ContractService(repository);

	}
}
