package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Contract;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ContractRepository {
    private final Map<String, Contract> contracts = new HashMap<>();

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
