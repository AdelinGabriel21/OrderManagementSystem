package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.model.Type;
import com.OrderManagementSystem.app.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractType", new ContractType("", Type.SELLER));
        model.addAttribute("types", Type.values());
        return "contractType/form";
    }

    @PostMapping
    public String createContractType(@ModelAttribute ContractType contractType) {
        service.saveContractType(contractType);
        return "redirect:/contract-types";
    }

    @PostMapping("/{id}/delete")
    public String deleteContractType(@PathVariable String id) {
        service.deleteContractType(id);
        return "redirect:/contract-types";
    }
}
