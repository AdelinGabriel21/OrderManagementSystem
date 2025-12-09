package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.model.Type;
import com.OrderManagementSystem.app.service.ContractTypeService;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllContractTypes(Model model) {
        model.addAttribute("contractTypes", service.getAllContractTypes());
        return "contractType/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractType contractType = service.getContractTypesById(id);
        if (contractType == null) {
            return "redirect:/contract-types";
        }
        model.addAttribute("contractType", contractType);
        model.addAttribute("types", Type.values());
        return "contractType/form";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractType", new ContractType());
        model.addAttribute("types", Type.values());
        return "contractType/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        ContractType contractType = service.getContractTypesById(id);
        if (contractType == null) {
            return "redirect:/contract-types";
        }
        model.addAttribute("contractType", contractType);
        return "contractType/details";
    }

    @PostMapping
    public String createContractType(@Valid @ModelAttribute ContractType contractType, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", Type.values());
            return "contractType/form";
        }

        try {
            service.saveContractType(contractType);
        } catch (ValidationException e) {
            bindingResult.reject("validation.business.error", e.getMessage());
            model.addAttribute("types", Type.values());
            return "contractType/form";
        }

        return "redirect:/contract-types";
    }

    @PostMapping("/{id}/delete")
    public String deleteContractType(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteContractType(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error", "in_use");
            return "redirect:/contract-types";
        }
        return "redirect:/contract-types";
    }
}
