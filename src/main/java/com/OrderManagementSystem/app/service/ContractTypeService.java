package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.model.Type;
import com.OrderManagementSystem.app.repository.ContractTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeService {
    private final ContractTypeRepository repo;

    public ContractTypeService(ContractTypeRepository repo) {
        this.repo = repo;
    }

    public void saveContractType(ContractType contractType) {
        repo.save(contractType);
    }

    public List<ContractType> getAllContractTypes() {
        return repo.findAll();
    }

    public ContractType getContractTypesById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteContractType(String id) {
        repo.delete(getContractTypesById(id));
    }

}
