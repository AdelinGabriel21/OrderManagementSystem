package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order("", null, null, new ArrayList<>()));
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("contracts", contractService.getAllContracts());
        return "order/form";
    }

    @PostMapping
    public String addOrder(
            @RequestParam String name,
            @RequestParam String customerId,
            @RequestParam String contractId
    ) {
        Customer customer = customerService.getCustomerById(customerId);
        Contract contract = contractService.getContractsById(contractId);

        Order order = new Order(name, customer, contract, new ArrayList<>());

        orderService.addOrder(order);
        return "redirect:/orders";
    }


    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
