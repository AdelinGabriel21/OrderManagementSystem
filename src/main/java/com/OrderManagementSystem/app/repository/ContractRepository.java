package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Contract;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepository extends InFileRepo<Contract> {
    public ContractRepository() {
        super("contract.json", Contract.class);
    }
}
