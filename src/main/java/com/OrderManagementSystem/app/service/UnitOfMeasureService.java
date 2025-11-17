package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.repository.UnitOfMeasureRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository repo;

    public UnitOfMeasureService(UnitOfMeasureRepository repo) {
        this.repo = repo;
    }

    public void saveUnitOfMeasure(UnitOfMeasure unit){
        repo.save(unit);
    }

    public List<UnitOfMeasure> getAllUnitsOfMeasure(){
        return repo.findAll();
    }

    public UnitOfMeasure getUnitOfMeasureById(String id){
        return repo.findById(id);
    }

    public void deleteUnitOfMeasure(String id){
        repo.delete(id);
    }

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            UnitOfMeasure u1 = new UnitOfMeasure(
                    "Kilogram",
                    "kg"
            );

            UnitOfMeasure u2 = new UnitOfMeasure(
                    "Meter",
                    "m"
            );

            UnitOfMeasure u3 = new UnitOfMeasure(
                    "Liter",
                    "L"
            );

            repo.save(u1);
            repo.save(u2);
            repo.save(u3);
        }
    }

}
