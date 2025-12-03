package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.service.ContractService;
import com.OrderManagementSystem.app.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;
    private final ContractTypeService contractTypeService;

    public ContractController(ContractService service, ContractTypeService contractTypeService) {
        this.service = service;
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public String listContracts(Model model) {
        model.addAttribute("contracts", service.getAllContracts());
        return "contract/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Contract contract = service.getContractsById(id);
        if (contract == null) {
            return "redirect:/contracts";
        }
        model.addAttribute("contract", contract);
        return "contract/form";
    }

    @GetMapping("/new")
    public String newContractForm(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("contractTypes", contractTypeService.getAllContractTypes());
        return "contract/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Contract contract = service.getContractsById(id);
        if (contract == null) {
            return "redirect:/contracts";
        }
        model.addAttribute("contract", contract);
        return "contract/details";
    }

    @PostMapping
    public String addContract(@Valid @ModelAttribute Contract contract,
                              BindingResult bindingResult,
                              @RequestParam(required = false) String contractTypeId) {

        if (contractTypeId != null && !contractTypeId.isEmpty()) {
            ContractType type = contractTypeService.getContractTypesById(contractTypeId);
            contract.setContractType(type);
        }

        if (contract.getContractType() == null) {
            bindingResult.rejectValue("contractType", "error.contractType", "Invalid or missing Contract Type");
        }

        if (bindingResult.hasErrors()) {
            return "contract/form";
        }

        service.saveContract(contract);
        return "redirect:/contracts";
    }

    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable String id) {
        service.deleteContracts(id);
        return "redirect:/contracts";
    }
}
