package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeService {
    private final ContractTypeRepository repo;

    public ContractTypeService(ContractTypeRepository repo) {
        this.repo = repo;
    }

    public void addContractType(ContractType contractType) {
        repo.save(contractType);
    }

    public List<ContractType> getAllContractTypes() {
        return repo.findAll();
    }

    public ContractType getContractTypesById(String id) {
        return repo.findById(id);
    }

    public void deleteContractType(String id) {
        repo.delete(id);
    }
}
