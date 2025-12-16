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

        Sort sort = Sort.unsorted();

        if (sortField1 != null && !sortField1.equals("none")) {
            Sort s1 = sortDir1.equalsIgnoreCase("asc") ?
                    Sort.by(sortField1).ascending() :
                    Sort.by(sortField1).descending();
            sort = s1;
        }

        if (sortField2 != null && !sortField2.equals("none")) {
            Sort s2 = sortDir2.equalsIgnoreCase("asc") ?
                    Sort.by(sortField2).ascending() :
                    Sort.by(sortField2).descending();

            if (sort.isSorted()) {
                sort = sort.and(s2);
            } else {
                sort = s2;
            }
        }

        return repo.searchContractTypes(name, type, sort);
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
