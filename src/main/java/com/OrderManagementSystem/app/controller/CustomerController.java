package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.service.CustomerService;
import jakarta.validation.ValidationException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllCustomers(Model model,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String currency,
                                   @RequestParam(defaultValue = "name") String sortField1,
                                   @RequestParam(defaultValue = "asc") String sortDir1,
                                   @RequestParam(defaultValue = "id") String sortField2,
                                   @RequestParam(defaultValue = "asc") String sortDir2) {

        model.addAttribute("customers", service.searchCustomers(name, email, currency, sortField1, sortDir1, sortField2, sortDir2));

        model.addAttribute("filterName", name);
        model.addAttribute("filterEmail", email);
        model.addAttribute("filterCurrency", currency);

        model.addAttribute("sortField1", sortField1);
        model.addAttribute("sortDir1", sortDir1);
        model.addAttribute("sortField2", sortField2);
        model.addAttribute("sortDir2", sortDir2);

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
