package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String newProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @PostMapping
    public String addContract(@ModelAttribute Product product){
        service.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable String id){
        service.deleteProduct(id);
        return "redirect:/products";
    }
}
