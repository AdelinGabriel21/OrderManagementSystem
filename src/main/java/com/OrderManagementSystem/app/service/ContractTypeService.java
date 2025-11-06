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
        return repo.findById(id);
    }

    public void deleteContractType(String id) {
        repo.delete(id);
    }

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            ContractType ct1 = new ContractType("Standard Seller Contract", Type.SELLER);
            ContractType ct2 = new ContractType("Standard Customer Contract", Type.CUSTOMER);
            ContractType ct3 = new ContractType("Premium Seller Contract", Type.SELLER);

            saveContractType(ct1);
            saveContractType(ct2);
            saveContractType(ct3);
        }
    }
}
