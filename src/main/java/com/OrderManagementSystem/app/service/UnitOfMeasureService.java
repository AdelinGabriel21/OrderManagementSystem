package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.repository.UnitOfMeasureRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository repo;

    public UnitOfMeasureService(UnitOfMeasureRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveUnitOfMeasure(UnitOfMeasure unit){
        validateBusinessRules(unit);
        repo.save(unit);
    }


    public List<UnitOfMeasure> searchUnits(String name, String symbol,
                                           String sortField1, String sortDir1,
                                           String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchUnits(name, symbol, sort1.and(sort2));
    }

    public UnitOfMeasure getUnitOfMeasureById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteUnitOfMeasure(String id){
        repo.delete(getUnitOfMeasureById(id));
    }

    public void validateBusinessRules(UnitOfMeasure unit) {
        List<UnitOfMeasure> allUnits = repo.findAll();

        boolean nameExists = allUnits.stream()
                .anyMatch(existingUnit ->
                        existingUnit.getName().equalsIgnoreCase(unit.getName()) &&
                                (unit.getId() == null || !existingUnit.getId().equals(unit.getId()))
                );

        if (nameExists) {
            throw new ValidationException("A unit of measure with the name '" + unit.getName() + "' already exists.");
        }

        boolean symbolExists = allUnits.stream()
                .anyMatch(existingUnit ->
                        existingUnit.getSymbol().equalsIgnoreCase(unit.getSymbol()) &&
                                (unit.getId() == null || !existingUnit.getId().equals(unit.getId()))
                );

        if (symbolExists) {
            throw new ValidationException("A unit of measure with the symbol '" + unit.getSymbol() + "' already exists.");
        }
    }
}