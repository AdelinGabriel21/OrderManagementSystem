package com.OrderManagementSystem.app.service;


import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository repo;

    public ContractService(ContractRepository repo) {
        this.repo = repo;
    }

    public void addContract(Contract contract){
        repo.save(contract);
    }

    public List<Contract> getAllContracts(){
        return repo.findAll();
    }

    public Contract getContractsById(String id){
        return repo.findById(id);
    }

    public void deleteContracts(String id){
        repo.delete(id);
    }
}
