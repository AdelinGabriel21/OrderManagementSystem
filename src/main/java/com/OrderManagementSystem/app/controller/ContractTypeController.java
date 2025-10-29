package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.model.Type;
import com.OrderManagementSystem.app.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    @GetMapping("/test-contract-types")
    @ResponseBody
    public String testContractTypes() {
        ContractType t1 = new ContractType("1", "Seller Contract", Type.SELLER);
        ContractType t2 = new ContractType("2", "Customer Contract", Type.CUSTOMER);
        ContractType t3 = new ContractType("3", "Seller Contract", Type.SELLER);

        service.addContractType(t1);
        service.addContractType(t2);
        service.addContractType(t3);

        var allTypes = service.getAllContractTypes();

        StringBuilder sb = new StringBuilder("All Contract Types in Repository:<br>");
        allTypes.forEach(ct -> sb.append(ct).append("<br>"));

        return sb.toString();
    }
}
