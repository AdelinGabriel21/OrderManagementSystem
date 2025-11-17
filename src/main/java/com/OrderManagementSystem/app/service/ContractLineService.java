package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.ContractLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.SellableItem;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.repository.ContractLineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractLineService {
    private final ContractLineRepository repo;

    public ContractLineService(ContractLineRepository repo) {
        this.repo = repo;
    }

    public void saveContractLine(ContractLine contractLine) {
        repo.save(contractLine);
    }

    public List<ContractLine> getAllContractLines() {
        return repo.findAll();
    }

    public ContractLine getContractLineById(String id) {
        return repo.findById(id);
    }

    public void deleteContractLine(String id) {
        repo.delete(id);
    }

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            Product item1 = new Product("Laptop", 1500.0, 20);
            Product item2 = new Product("Mouse", 25.0, 50);

            UnitOfMeasure u1 = new UnitOfMeasure("Kilogram", "kg");
            UnitOfMeasure u2 = new UnitOfMeasure("Meter", "m");

            ContractLine line1 = new ContractLine(item1, u1, 10);
            ContractLine line2 = new ContractLine(item2, u2, 5);

            saveContractLine(line1);
            saveContractLine(line2);
        }
    }
}
