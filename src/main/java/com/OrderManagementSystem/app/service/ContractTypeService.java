package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.model.Type;
import com.OrderManagementSystem.app.repository.ContractTypeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeService {
    private final ContractTypeRepository repo;

    @Autowired
    public ContractTypeService(ContractTypeRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveContractType(ContractType contractType) {
        validateBusinessRules(contractType);
        repo.save(contractType);
    }

    public List<ContractType> searchContractTypes(String name, Type type,
                                                  String sortField1, String sortDir1,
                                                  String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchContractTypes(name, type, sort1.and(sort2));
    }

    public ContractType getContractTypesById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteContractType(String id) {
        repo.delete(getContractTypesById(id));
    }

    public void validateBusinessRules(ContractType contractType) {
        List<ContractType> allTypes = repo.findAll();

        boolean nameExists = allTypes.stream()
                .anyMatch(existing ->
                        existing.getName().equalsIgnoreCase(contractType.getName()) &&
                                // Allow if it's the same entity (ID matches)
                                (contractType.getId() == null || !existing.getId().equals(contractType.getId()))
                );

        if (nameExists) {
            throw new ValidationException("A contract type with the name '" + contractType.getName() + "' already exists.");
        }
    }
}
