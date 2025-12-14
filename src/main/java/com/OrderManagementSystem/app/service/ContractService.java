package com.OrderManagementSystem.app.service;


import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.ContractRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ContractService {
    private final ContractRepository repo;

    @Autowired
    public ContractService(ContractRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveContract(Contract contract){
        validateBusinessRules(contract);
        repo.save(contract);
    }

    public List<Contract> getAllContracts(){
        return repo.findAll();
    }

    @Transactional
    public Contract getContractsById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteContracts(String id){
        repo.delete(getContractsById(id));
    }

    public void validateBusinessRules(Contract contract) {
        Date creationDate = contract.getCreationDate();
        Date expirationDate = contract.getExpirationDate();

        if (creationDate != null && expirationDate != null && expirationDate.before(creationDate)) {
            throw new ValidationException("The Expiration Date cannot be before the Creation Date.");
        }
    }

    public List<Contract> searchContracts(String name, Status status, Date fromDate, Date toDate) {
        return repo.searchContracts(name, status, fromDate, toDate);
    }
}
