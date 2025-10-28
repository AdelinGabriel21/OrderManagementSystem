package com.OrderManagementSystem.app.controller;


import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UnitOfMeasureController {

    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service) {
        this.service = service;
    }

    public void testAll(){
        UnitOfMeasure p1 = new UnitOfMeasure("1", "kilogram", "kg");
        UnitOfMeasure p2 = new UnitOfMeasure("2", "meter", "m");
        UnitOfMeasure p3 = new UnitOfMeasure("3", "second", "s");
        service.addUnitOfMeasure(p1);
        service.addUnitOfMeasure(p2);
        service.addUnitOfMeasure(p3);

        List<UnitOfMeasure> allUnitsOfMeasure = service.getAllUnitsOfMeasure();
        System.out.println("All products in repository:");
        for (UnitOfMeasure u : allUnitsOfMeasure) {
            System.out.println(u);
        }

        UnitOfMeasure fetched = service.getUnitOfMeasureById("2");
        System.out.println("Fetched product with ID 2: " + fetched);

        service.deleteUnitOfMeasure("3");
        System.out.println("After deleting ID 3:");
        for (UnitOfMeasure u : allUnitsOfMeasure) {
            System.out.println(u);
        }

        System.out.println("UnitOfMeasureService test completed.");
    }
}
