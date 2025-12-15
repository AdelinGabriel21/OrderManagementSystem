package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/unitsOfMeasure")
public class UnitOfMeasureController {

    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service) {
        this.service = service;
    }

    @GetMapping
    public String showUnitsOfMeasure(Model model,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String symbol,
                                     @RequestParam(defaultValue = "name") String sortField,
                                     @RequestParam(defaultValue = "asc") String sortDir) {

        model.addAttribute("unitsOfMeasure", service.searchUnits(name, symbol, sortField, sortDir));

        model.addAttribute("filterName", name);
        model.addAttribute("filterSymbol", symbol);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "unitOfMeasure/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        UnitOfMeasure unitOfMeasure = service.getUnitOfMeasureById(id);
        if (unitOfMeasure == null) {
            return "redirect:/unitsOfMeasure";
        }
        model.addAttribute("unitOfMeasure", unitOfMeasure);
        return "unitOfMeasure/form";
    }

    @GetMapping("/new")
    public String newUnitOfMeasureForm(Model model) {
        model.addAttribute("unitOfMeasure", new UnitOfMeasure());
        return "unitOfMeasure/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        UnitOfMeasure unitOfMeasure = service.getUnitOfMeasureById(id);
        if (unitOfMeasure == null) {
            return "redirect:/unitsOfMeasure";
        }
        model.addAttribute("unitOfMeasure", unitOfMeasure);
        return "unitOfMeasure/details";
    }

    @PostMapping
    public String addUnitOfMeasure(@Valid @ModelAttribute UnitOfMeasure unitOfMeasure, BindingResult bindingResult, Model model) { // Added Model

        if (bindingResult.hasErrors()) {
            return "unitOfMeasure/form";
        }

        try {
            service.saveUnitOfMeasure(unitOfMeasure);
        } catch (ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());
            return "unitOfMeasure/form";
        }

        if (bindingResult.hasErrors()) {
            return "unitOfMeasure/form";
        }

        service.saveUnitOfMeasure(unitOfMeasure);
        return "redirect:/unitsOfMeasure";
    }

    @PostMapping("/{id}/delete")
    public String deleteUnitOfMeasure(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteUnitOfMeasure(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error", "in_use");
            return "redirect:/unitsOfMeasure";
        }
        return "redirect:/unitsOfMeasure";
    }
}
