package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.service.CustomerService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.ArrayList;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", service.getAllCustomers());
        return "customer/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "customer/form";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "customer/details";
    }

    @PostMapping
    public String createCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "customer/form";
        }

        try {
            service.saveCustomer(customer);
        } catch (ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());
            return "customer/form";
        }

        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }
}
