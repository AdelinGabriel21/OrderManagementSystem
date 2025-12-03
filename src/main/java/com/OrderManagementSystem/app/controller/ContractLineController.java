package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.ContractLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.ContractLineService;
import com.OrderManagementSystem.app.service.ContractService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/contract-lines")
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
    public String listContractLines(Model model) {
        model.addAttribute("contractLines", service.getAllContractLines());
        return "contractLine/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractLine contractLine = service.getContractLineById(id);
        if (contractLine == null) {
            return "redirect:/contract-lines";
        }
        model.addAttribute("contractLine", contractLine);
        model.addAttribute("product", productService.getAllProducts());
        model.addAttribute("unitOfMeasure", unitOfMeasureService.getAllUnitsOfMeasure());
        model.addAttribute("contracts", contractService.getAllContracts());
        return "contractLine/form";
    }

    @GetMapping("/new")
    public String newContractLineForm(Model model) {
        ContractLine line = new ContractLine();
        model.addAttribute("contractLine", line);
        model.addAttribute("product", productService.getAllProducts());
        model.addAttribute("unitOfMeasure", unitOfMeasureService.getAllUnitsOfMeasure());
        model.addAttribute("contracts", contractService.getAllContracts());

        return "contractLine/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        ContractLine contractLine = service.getContractLineById(id);
        if (contractLine == null) {
            return "redirect:/contract-lines";
        }
        model.addAttribute("contractLine", contractLine);
        return "contractLine/details";
    }


    @PostMapping
    public String addContractLine(
            @Valid @ModelAttribute ContractLine contractLine,
            BindingResult bindingResult,
            Model model,
            @RequestParam String productId,
            @RequestParam String unitOfMeasureId,
            @RequestParam String contractId
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", productService.getAllProducts());
            model.addAttribute("unitOfMeasure", unitOfMeasureService.getAllUnitsOfMeasure());
            model.addAttribute("contracts", contractService.getAllContracts());
            return "contractLine/form";
        }

        Product product = productService.getProductById(productId);
        UnitOfMeasure unitOfMeasure = unitOfMeasureService.getUnitOfMeasureById(unitOfMeasureId);
        Contract contract = contractService.getContractsById(contractId);

        contractLine.setItem(product);
        contractLine.setUnit(unitOfMeasure);
        contractLine.setContract(contract);

        service.saveContractLine(contractLine);
        return "redirect:/contract-lines";
    }


    @PostMapping("/{id}/delete")
    public String deleteContractLine(@PathVariable String id) {
        service.deleteContractLine(id);
        return "redirect:/contract-lines";
    }
}
