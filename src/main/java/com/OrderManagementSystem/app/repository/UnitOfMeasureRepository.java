package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import java.util.ArrayList;
import java.util.Map;

public class UnitOfMeasureRepository {
    private Map<String, UnitOfMeasure> units;

    public UnitOfMeasureRepository(Map<String, UnitOfMeasure> units) {
        this.units = units;
    }

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
