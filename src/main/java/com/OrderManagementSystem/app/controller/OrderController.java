package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

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
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "order/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("contracts", contractService.getAllContracts());
        return "order/form";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order("", null, null, new ArrayList<>()));
        model.addAttribute("customers", customerService.getAllCustomers());
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
                           Model model,
                           @RequestParam(required = false) String customerId,
                           @RequestParam(required = false) String contractId) {

        if (bindingResult.hasErrors()) {
            // Reload lists so the dropdowns don't disappear on error
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("contracts", contractService.getAllContracts());
            return "order/form";
        }

        if (customerId != null && !customerId.isEmpty()) {
            order.setCustomer(customerService.getCustomerById(customerId));
        }
        if (contractId != null && !contractId.isEmpty()) {
            order.setContract(contractService.getContractsById(contractId));
        }

        orderService.saveOrder(order);
        return "redirect:/orders";
    }


    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
