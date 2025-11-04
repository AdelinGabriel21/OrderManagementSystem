package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping
    public String listContracts(Model model) {
        model.addAttribute("contracts", service.getAllContracts());
        return "contract/index";
    }

    @GetMapping("/new")
    public String newContractForm(Model model) {
        model.addAttribute("contract", new Contract());
        return "contract/form";
    }

    @PostMapping
    public String addContract(@ModelAttribute Contract contract) {
        service.addContract(contract);
        return "redirect:/contracts";
    }

    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable String id) {
        service.deleteContracts(id);
        return "redirect:/contracts";
    }
}
