package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UnitOfMeasureRepository {
    private Map<String, UnitOfMeasure> units = new HashMap<>();

    public void save(UnitOfMeasure unit){
        units.put(unit.getId(),unit);
    }

    public ArrayList<UnitOfMeasure> findAll(){
        return new ArrayList<>(units.values());
    }

    public UnitOfMeasure findById(String id){
        return units.get(id);
    }

    public void delete(String id){
        units.remove(id);
    }
}
