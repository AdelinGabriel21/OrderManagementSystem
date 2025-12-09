package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.service.ContractService;
import com.OrderManagementSystem.app.service.ContractTypeService;
import com.OrderManagementSystem.app.service.CustomerService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import java.beans.PropertyEditorSupport;


@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;
    private final CustomerService customerService;
    private final ContractTypeService contractTypeService;

    public ContractController(ContractService service, CustomerService customerService, ContractTypeService contractTypeService) {
        this.service = service;
        this.customerService = customerService;
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
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("contractTypes", contractTypeService.getAllContractTypes());
        return "contract/form";
    }

    @GetMapping("/new")
    public String newContractForm(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("customers", customerService.getAllCustomers());
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
    public String addContract(@Valid @ModelAttribute Contract submittedContract,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("contractTypes", contractTypeService.getAllContractTypes());
            return "contract/form";
        }

        try {
            if (submittedContract.getId() != null && !submittedContract.getId().isEmpty()) {
                Contract existingContract = service.getContractsById(submittedContract.getId());

                existingContract.setName(submittedContract.getName());
                existingContract.setContractTypeId(submittedContract.getContractTypeId());
                existingContract.setStatus(submittedContract.getStatus());
                existingContract.setCreationDate(submittedContract.getCreationDate());
                existingContract.setExpirationDate(submittedContract.getExpirationDate());
                existingContract.setCustomer(submittedContract.getCustomer());

                submittedContract = existingContract;
            }

            service.saveContract(submittedContract);

        } catch (ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());

            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("contractTypes", contractTypeService.getAllContractTypes());
            return "contract/form";
        }

        return "redirect:/contracts";
    }

    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable String id) {
        service.deleteContracts(id);
        return "redirect:/contracts";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Customer.class, "customer", new PropertyEditorSupport() {
            @Override
            public void setAsText(String customerId) {
                if (customerId != null && !customerId.isEmpty()) {
                    Customer customer = customerService.getCustomerById(customerId);
                    setValue(customer);
                } else {
                    setValue(null);
                }
            }
        });
    }
}
