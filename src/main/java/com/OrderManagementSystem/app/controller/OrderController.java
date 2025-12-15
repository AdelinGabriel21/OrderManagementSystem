package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.*;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.beans.PropertyEditorSupport;


@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ContractService contractService;

    public OrderController(OrderService orderService, CustomerService customerService, ContractService contractService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.contractService = contractService;
    }

    @GetMapping
    public String showOrders(Model model,
                             // Filter Params
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String customerName,
                             @RequestParam(required = false) String contractName,
                             // Sort Params
                             @RequestParam(defaultValue = "name") String sortField1,
                             @RequestParam(defaultValue = "asc") String sortDir1,
                             @RequestParam(defaultValue = "name") String sortField2,
                             @RequestParam(defaultValue = "asc") String sortDir2) {

        model.addAttribute("orders", orderService.searchOrders(name, customerName, contractName, sortField1, sortDir1, sortField2, sortDir2));

        model.addAttribute("filterName", name);
        model.addAttribute("filterCustomerName", customerName);
        model.addAttribute("filterContractName", contractName);

        model.addAttribute("sortField1", sortField1);
        model.addAttribute("sortDir1", sortDir1);
        model.addAttribute("sortField2", sortField2);
        model.addAttribute("sortDir2", sortDir2);

        return "order/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.searchCustomers(null, null, null, "name", "asc", "name", "asc"));
        model.addAttribute("contracts", contractService.getAllContracts());
        return "order/form";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.searchCustomers(null, null, null, "name", "asc", "name", "asc"));
        model.addAttribute("contracts", contractService.getAllContracts());
        return "order/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        return "order/details";
    }

    @PostMapping
    public String addOrder(@Valid @ModelAttribute Order order,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.searchCustomers(null, null, null, "name", "asc", "name", "asc"));
            model.addAttribute("contracts", contractService.getAllContracts());
            return "order/form";
        }

        try {
            orderService.saveOrder(order);
        } catch (ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());

            model.addAttribute("customers", customerService.searchCustomers(null, null, null, "name", "asc", "name", "asc"));
            model.addAttribute("contracts", contractService.getAllContracts());
            return "order/form";
        }

        return "redirect:/orders";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Customer.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String customerId) {
                setValue((customerId != null && !customerId.isEmpty()) ? customerService.getCustomerById(customerId) : null);
            }
        });

        binder.registerCustomEditor(Contract.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String contractId) {
                setValue((contractId != null && !contractId.isEmpty()) ? contractService.getContractsById(contractId) : null);
            }
        });
    }
}
