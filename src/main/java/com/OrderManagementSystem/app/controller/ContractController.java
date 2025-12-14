package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.service.ContractService;
import com.OrderManagementSystem.app.service.ContractTypeService;
import com.OrderManagementSystem.app.service.CustomerService;
import jakarta.validation.ValidationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import java.beans.PropertyEditorSupport;
import java.util.Date;


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
    public String showContracts(Model model,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) Status status,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
                                @RequestParam(defaultValue = "name") String sortField1,
                                @RequestParam(defaultValue = "asc") String sortDir1,
                                @RequestParam(defaultValue = "creationDate") String sortField2,
                                @RequestParam(defaultValue = "desc") String sortDir2) {

        model.addAttribute("contracts", service.searchContracts(name, status, fromDate, toDate, sortField1, sortDir1, sortField2, sortDir2));

        model.addAttribute("filterName", name);
        model.addAttribute("filterStatus", status);
        model.addAttribute("filterFromDate", fromDate);
        model.addAttribute("filterToDate", toDate);
        model.addAttribute("statusOptions", Status.values());

        model.addAttribute("sortField1", sortField1);
        model.addAttribute("sortDir1", sortDir1);
        model.addAttribute("sortField2", sortField2);
        model.addAttribute("sortDir2", sortDir2);

        return "contract/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Contract contract = service.getContractsById(id);
        if (contract == null) {
            return "redirect:/contracts";
        }
        model.addAttribute("contract", contract);
        model.addAttribute("customers", customerService.getAllCustomers("name", "asc", "name", "asc"));
        model.addAttribute("contractTypes", contractTypeService.getAllContractTypes());
        return "contract/form";
    }

    @GetMapping("/new")
    public String newContractForm(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("customers", customerService.getAllCustomers("name", "asc", "name", "asc"));
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
            model.addAttribute("customers", customerService.getAllCustomers("name", "asc", "name", "asc"));
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

            model.addAttribute("customers", customerService.getAllCustomers("name", "asc", "name", "asc"));
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
