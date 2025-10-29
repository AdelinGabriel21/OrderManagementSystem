package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UnitOfMeasureController {

    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service) {
        this.service = service;
    }

    @GetMapping("/test-units")
    @ResponseBody
    public String testUnitsOfMeasure() {
        UnitOfMeasure u1 = new UnitOfMeasure("1", "kilogram", "kg");
        UnitOfMeasure u2 = new UnitOfMeasure("2", "meter", "m");
        UnitOfMeasure u3 = new UnitOfMeasure("3", "second", "s");

        service.addUnitOfMeasure(u1);
        service.addUnitOfMeasure(u2);
        service.addUnitOfMeasure(u3);

        List<UnitOfMeasure> allUnits = service.getAllUnitsOfMeasure();

        StringBuilder sb = new StringBuilder("All units of measure in repository:<br>");
        allUnits.forEach(u -> sb.append(u).append("<br>"));

        UnitOfMeasure fetched = service.getUnitOfMeasureById("2");
        sb.append("<br>Fetched unit with ID 2: ").append(fetched).append("<br>");

        service.deleteUnitOfMeasure("3");
        sb.append("<br>After deleting unit with ID 3:<br>");
        service.getAllUnitsOfMeasure().forEach(u -> sb.append(u).append("<br>"));

        sb.append("<br>UnitOfMeasureService test completed.");

        return sb.toString();
    }
}
