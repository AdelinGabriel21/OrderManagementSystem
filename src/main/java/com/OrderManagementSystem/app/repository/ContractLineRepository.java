package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractLine;
import com.OrderManagementSystem.app.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ContractLineRepository extends InFileRepo<ContractLine> {
    public ContractLineRepository() {
        super("contractLine.json", ContractLine.class);
    }
}
