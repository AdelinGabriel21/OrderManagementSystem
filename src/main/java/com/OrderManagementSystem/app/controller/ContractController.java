package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Date;

@Controller
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping("/test-contracts")
    @ResponseBody
    public String testContracts() {
        Contract contract = new Contract(
                "1",
                "Example Contract",
                "TYPE1",
                Status.ACTIVE,
                Collections.emptyList(),
                new Date(),
                new Date()
        );

        service.addContract(contract);

        return "Contract added successfully! Total contracts: " + service.getAllContracts().size();
    }
}
