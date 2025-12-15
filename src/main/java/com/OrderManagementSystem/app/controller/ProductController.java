package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.service.ProductService;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String showProducts(Model model,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) Double minValue,
                               @RequestParam(required = false) Double maxValue,
                               @RequestParam(defaultValue = "name") String sortField,
                               @RequestParam(defaultValue = "asc") String sortDir) {

        model.addAttribute("products", service.searchProducts(name, minValue, maxValue, sortField, sortDir));

        model.addAttribute("filterName", name);
        model.addAttribute("filterMinValue", minValue);
        model.addAttribute("filterMaxValue", maxValue);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "product/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = service.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/form";
    }

    @GetMapping("/new")
    public String newProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Product product = service.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/details";
    }

    @PostMapping
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "product/form";
        }

        try {
            service.validateBusinessRules(product);
        } catch (ValidationException e) {
            bindingResult.reject("validation.business.error", e.getMessage());
        }

        if (bindingResult.hasErrors()) {
            return "product/form";
        }

        service.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteProduct(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error", "in_use");
            return "redirect:/products";
        }
        return "redirect:/products";
    }
}
