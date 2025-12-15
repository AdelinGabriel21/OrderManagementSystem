package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.ContractLine;
import com.OrderManagementSystem.app.model.SellableItem;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.repository.ContractLineRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractLineService {
    private final ContractLineRepository repo;

    @Autowired
    public ContractLineService(ContractLineRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveContractLine(ContractLine contractLine) {
        validateBusinessRules(contractLine);
        repo.save(contractLine);
    }

    public List<ContractLine> searchContractLines(String contractName, String itemName, String unitName,
                                                  String sortField1, String sortDir1,
                                                  String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchContractLines(contractName, itemName, unitName, sort1.and(sort2));
    }

    public ContractLine getContractLineById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteContractLine(String id) {
        repo.delete(getContractLineById(id));
    }


    public void validateBusinessRules(ContractLine contractLine) {
        SellableItem item = contractLine.getItem();
        UnitOfMeasure unit = contractLine.getUnit();

        if (item == null || unit == null) return;

        if (item.getType().equalsIgnoreCase("product")) {
            if (unit.getSymbol().equalsIgnoreCase("s") || unit.getSymbol().equalsIgnoreCase("h")) {
                throw new ValidationException("Physical products cannot be measured in time (seconds/hours).");
            }
        }
        else if (item.getType().equalsIgnoreCase("service")) {
        }
    }
}
