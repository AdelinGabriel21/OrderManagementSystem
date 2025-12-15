package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.ContractLineService;
import com.OrderManagementSystem.app.service.ContractService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor; // Don't forget this!
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/contractLines")
public class ContractLineController {

    private final ContractLineService service;
    private final ProductService productService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final ContractService contractService;

    public ContractLineController(ContractLineService service, ProductService productService, UnitOfMeasureService unitOfMeasureService, ContractService contractService) {
        this.service = service;
        this.productService = productService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.contractService = contractService;
    }

    @GetMapping
    public String showContractLines(Model model,
                                    @RequestParam(required = false) String contractName,
                                    @RequestParam(required = false) String itemName,
                                    @RequestParam(required = false) String unitName,
                                    @RequestParam(defaultValue = "quantity") String sortField1,
                                    @RequestParam(defaultValue = "asc") String sortDir1,
                                    @RequestParam(defaultValue = "item.name") String sortField2, // Default Secondary
                                    @RequestParam(defaultValue = "asc") String sortDir2) {

        model.addAttribute("contractLines", service.searchContractLines(contractName, itemName, unitName, sortField1, sortDir1, sortField2, sortDir2));

        model.addAttribute("filterContractName", contractName);
        model.addAttribute("filterItemName", itemName);
        model.addAttribute("filterUnitName", unitName);

        model.addAttribute("sortField1", sortField1);
        model.addAttribute("sortDir1", sortDir1);
        model.addAttribute("sortField2", sortField2);
        model.addAttribute("sortDir2", sortDir2);

        return "contractLine/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractLine contractLine = service.getContractLineById(id);
        if (contractLine == null) {
            return "redirect:/contractLines";
        }
        model.addAttribute("contractLine", contractLine);
        populateDependencies(model);
        return "contractLine/form";
    }

    @GetMapping("/new")
    public String newContractLineForm(Model model) {
        model.addAttribute("contractLine", new ContractLine());
        populateDependencies(model);
        return "contractLine/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        ContractLine contractLine = service.getContractLineById(id);
        if (contractLine == null) {
            return "redirect:/contractLines";
        }
        model.addAttribute("contractLine", contractLine);
        return "contractLine/details";
    }

    @PostMapping
    public String addContractLine(@Valid @ModelAttribute ContractLine contractLine,
                                  BindingResult bindingResult,
                                  Model model) {

        if (bindingResult.hasErrors()) {
            populateDependencies(model);
            return "contractLine/form";
        }

        try {
            service.saveContractLine(contractLine);
        } catch (jakarta.validation.ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());
            populateDependencies(model);
            return "contractLine/form";
        }

        return "redirect:/contractLines";
    }

    @PostMapping("/{id}/delete")
    public String deleteContractLine(@PathVariable String id) {
        service.deleteContractLine(id);
        return "redirect:/contractLines";
    }

    private void populateDependencies(Model model) {
        model.addAttribute("products", productService.searchProducts(null, null, null, "name", "asc"));
        model.addAttribute("units", unitOfMeasureService.searchUnits(null, null, "name","asc"));
        model.addAttribute("contracts", contractService.getAllContracts());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        binder.registerCustomEditor(SellableItem.class, "item", new PropertyEditorSupport() {
            @Override
            public void setAsText(String itemId) {
                setValue((itemId != null && !itemId.isEmpty()) ? productService.getProductById(itemId) : null);
            }
        });

        binder.registerCustomEditor(UnitOfMeasure.class, "unit", new PropertyEditorSupport() {
            @Override
            public void setAsText(String unitId) {
                setValue((unitId != null && !unitId.isEmpty()) ? unitOfMeasureService.getUnitOfMeasureById(unitId) : null);
            }
        });

        binder.registerCustomEditor(Contract.class, "contract", new PropertyEditorSupport() {
            @Override
            public void setAsText(String contractId) {
                setValue((contractId != null && !contractId.isEmpty()) ? contractService.getContractsById(contractId) : null);
            }
        });
    }
}