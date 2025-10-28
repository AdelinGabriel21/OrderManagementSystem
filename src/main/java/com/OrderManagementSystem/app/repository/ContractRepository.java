package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Contract;

import java.util.ArrayList;
import java.util.Map;

public class ContractRepository {
    private Map<String, Contract> contracts;

    public ContractRepository(Map<String, Contract> contracts) {
        this.contracts = contracts;
    }

    public void save(Contract contract) {
        contracts.put(contract.getId(), contract);
    }

    public ArrayList<Contract> findAll() {
        return new ArrayList<>(contracts.values());
    }

    public Contract findById(String id) {
        return contracts.get(id);
    }

    public void delete(String contractId) {
        contracts.remove(contractId);
    }
}
