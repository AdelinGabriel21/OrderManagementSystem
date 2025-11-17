package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import org.springframework.stereotype.Repository;

@Repository
public class UnitOfMeasureRepository extends InFileRepo<UnitOfMeasure> {
    public UnitOfMeasureRepository (){
        super("unitOfMeasure.json", UnitOfMeasure.class);
    }
}
