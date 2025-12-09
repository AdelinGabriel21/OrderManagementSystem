package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.repository.UnitOfMeasureRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
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

    public List<UnitOfMeasure> getAllUnitsOfMeasure(){
        return repo.findAll();
    }

    public UnitOfMeasure getUnitOfMeasureById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteUnitOfMeasure(String id){
        repo.delete(getUnitOfMeasureById(id));
    }

    public void validateBusinessRules(UnitOfMeasure unit) {
        List<UnitOfMeasure> allUnits = repo.findAll();

        // --- Business Rule 1: Unique Name Check ---
        boolean nameExists = allUnits.stream()
                .anyMatch(existingUnit ->
                        existingUnit.getName().equalsIgnoreCase(unit.getName()) &&
                                (unit.getId() == null || !existingUnit.getId().equals(unit.getId()))
                );

        if (nameExists) {
            throw new ValidationException("A unit of measure with the name '" + unit.getName() + "' already exists.");
        }

        // --- Business Rule 2: Unique Symbol Check ---
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
