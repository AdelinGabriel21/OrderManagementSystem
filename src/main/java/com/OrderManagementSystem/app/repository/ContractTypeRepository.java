package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ContractTypeRepository {
    private final Map<String, ContractType> contractTypes = new HashMap<>();

    public void save(ContractType contractType){
        contractTypes.put(contractType.getId(), contractType);
    }

    public ArrayList<ContractType> findAll(){
        return new ArrayList<>(contractTypes.values());
    }

    public ContractType findById(String id){
        return contractTypes.get(id);
    }

    public void delete(String contractTypeId){
        contractTypes.remove(contractTypeId);
    }
}

