package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ContractLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.ContractLineService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-lines")
public class ContractLineController {

    private final ContractLineService service;
    private final ProductService productService;
    private final UnitOfMeasureService unitOfMeasureService;

    public ContractLineController(ContractLineService service,  ProductService productService, UnitOfMeasureService unitOfMeasureService) {
        this.service = service;
        this.productService = productService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    public String listContractLines(Model model) {
        model.addAttribute("contractLines", service.getAllContractLines());
        return "contractLine/index";
    }

    @GetMapping("/new")
    public String newContractLineForm(Model model) {
        ContractLine line = new ContractLine(new Product("", 0.0, 0), new UnitOfMeasure("", ""), 0);
        model.addAttribute("contractLine", line);
        model.addAttribute("product", productService.getAllProducts());
        model.addAttribute("unitOfMeasure", unitOfMeasureService.getAllUnitsOfMeasure());
        return "contractLine/form";
    }


    @PostMapping
    public String addContractLine(
            @RequestParam String productId,
            @RequestParam String unitOfMeasureId,
            @RequestParam double quantity

    ) {
        Product product = productService.getProductById(productId);
        UnitOfMeasure unitOfMeasure = unitOfMeasureService.getUnitOfMeasureById(unitOfMeasureId);

        ContractLine contractLine = new ContractLine(product, unitOfMeasure, quantity);

        service.saveContractLine(contractLine);
        return "redirect:/contract-lines";
    }


    @PostMapping("/{id}/delete")
    public String deleteContractLine(@PathVariable String id) {
        service.deleteContractLine(id);
        return "redirect:/contract-lines";
    }
}
