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
                                                  String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        return repo.searchContractLines(contractName, itemName, unitName, sort);
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
