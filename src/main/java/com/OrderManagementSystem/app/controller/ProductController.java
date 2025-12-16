package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.service.ProductService;
import jakarta.validation.ValidationException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
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
                               @RequestParam(defaultValue = "name") String sortField1,
                               @RequestParam(defaultValue = "asc") String sortDir1,
                               @RequestParam(defaultValue = "value") String sortField2,
                               @RequestParam(defaultValue = "asc") String sortDir2) {

        model.addAttribute("products", service.searchProducts(name, minValue, maxValue, sortField1, sortDir1, sortField2, sortDir2));

        model.addAttribute("filterName", name);
        model.addAttribute("filterMinValue", minValue);
        model.addAttribute("filterMaxValue", maxValue);

        model.addAttribute("sortField1", sortField1);
        model.addAttribute("sortDir1", sortDir1);
        model.addAttribute("sortField2", sortField2);
        model.addAttribute("sortDir2", sortDir2);

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}