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

    @PostConstruct
    public void initData(){
        if (repo.findAll().isEmpty()) {
            Contract c1 = new Contract(
                    "Contract A",
                    "Type1",
                    Status.ACTIVE,
                    Arrays.asList(),
                    new Date(),
                    new Date(System.currentTimeMillis() + 86400000L) // +1 day
            );

            Contract c2 = new Contract(
                    "Contract B",
                    "Type2",
                    Status.DOWN,
                    Arrays.asList(),
                    new Date(),
                    new Date(System.currentTimeMillis() + 172800000L) // +2 days
            );

            addContract(c1);
            addContract(c2);
        }
    }
}
