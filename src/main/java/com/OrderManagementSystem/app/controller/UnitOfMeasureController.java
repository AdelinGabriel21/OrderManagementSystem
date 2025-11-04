package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/unitsOfMeasure")
public class UnitOfMeasureController {

    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service) {
        this.service = service;
    }

    @GetMapping
    public String listUnitsOfMeasure(Model model) {
        model.addAttribute("unitsOfMeasure", service.getAllUnitsOfMeasure());
        return "unitOfMeasure/index";
    }

    @GetMapping("/new")
    public String newUnitOfMeasureForm(Model model) {
        model.addAttribute("unitOfMeasure", new UnitOfMeasure());
        return "unitOfMeasure/form";
    }

    @PostMapping
    public String addUnitOfMeasure(@ModelAttribute UnitOfMeasure unitOfMeasure) {
        service.addUnitOfMeasure(unitOfMeasure);
        return "redirect:/unitsOfMeasure";
    }

    @PostMapping("/{id}/delete")
    public String deleteUnitOfMeasure(@PathVariable String id) {
        service.deleteUnitOfMeasure(id);
        return "redirect:/unitsOfMeasure";
    }
}
