package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractType;
import java.util.ArrayList;
import java.util.Map;

public class ContractTypeRepository {
    private Map<String, ContractType> contractTypes;

    public ContractTypeRepository(Map<String, ContractType> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public void save(ContractType contractType){
        contractTypes.put(contractType.getId(), contractType);
    }

    public ArrayList<ContractType> findAll(){
        return new ArrayList<>(contractTypes.values());
    }

    public ContractType finByID(String id){
        return contractTypes.get(id);
    }

    public void delete(String contractTypeId){
        contractTypes.remove(contractTypeId);
    }
}

