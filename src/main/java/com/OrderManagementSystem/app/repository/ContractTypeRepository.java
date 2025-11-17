package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractType;
import org.springframework.stereotype.Repository;

@Repository
public class ContractTypeRepository extends InFileRepo<ContractType> {
    public ContractTypeRepository() {
        super("contractType.json", ContractType.class);
    }
}

