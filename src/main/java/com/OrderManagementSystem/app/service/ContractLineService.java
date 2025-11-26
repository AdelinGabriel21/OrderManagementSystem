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
        return repo.findById(id).orElse(null);
    }

    public void deleteContractLine(String id) {
        repo.delete(getContractLineById(id));
    }

}
