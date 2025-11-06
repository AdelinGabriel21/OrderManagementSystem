package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "customer/index"; // templates/customer/index.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer("", "EUR", new ArrayList<>(), new ArrayList<>()));
        return "customer/form"; // templates/customer/form.html
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        service.saveCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }
}
