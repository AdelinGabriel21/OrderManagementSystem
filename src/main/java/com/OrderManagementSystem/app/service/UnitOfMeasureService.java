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
        return repo.findById(id).orElse(null);
    }

    public void deleteUnitOfMeasure(String id){
        repo.delete(getUnitOfMeasureById(id));
    }


}
