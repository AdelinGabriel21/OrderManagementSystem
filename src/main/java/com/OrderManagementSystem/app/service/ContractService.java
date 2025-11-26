package com.OrderManagementSystem.app.service;


import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.ContractRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ContractService {
    private final ContractRepository repo;

    public ContractService(ContractRepository repo) {
        this.repo = repo;
    }

    public void saveContract(Contract contract){
        repo.save(contract);
    }

    public List<Contract> getAllContracts(){
        return repo.findAll();
    }

    public Contract getContractsById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteContracts(String id){
        repo.delete(getContractsById(id));
    }

}
