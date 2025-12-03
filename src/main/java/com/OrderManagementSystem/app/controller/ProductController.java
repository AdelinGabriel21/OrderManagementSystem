package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.service.ProductService;
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
    public String listProducts(Model model){
        model.addAttribute("products", service.getAllProducts());
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
    public String addContract(@Valid @ModelAttribute Product product, BindingResult bindingResult){
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
