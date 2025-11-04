package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/test-orders")
    @ResponseBody
    public String testOrders() {
        Customer customer1 = new Customer("C1", "Adelin Cracea", "EUR", new ArrayList<>(), new ArrayList<>());
        Customer customer2 = new Customer("C2", "TechWorks GmbH", "USD", new ArrayList<>(), new ArrayList<>());

//        Contract contract1 = new Contract("Contract 1", "TYPE 1", Status.ACTIVE, Collections.emptyList(), new Date(), new Date());
//        Contract contract2 = new Contract("Contract 2",  "TYPE 2", Status.DOWN, Collections.emptyList(), new Date(), new Date());

        List<OrderLine> orderLines1 = new ArrayList<>();
        List<OrderLine> orderLines2 = new ArrayList<>();

//        Order o1 = new Order("O1", "Order 1", customer1, contract1, orderLines1);
//        Order o2 = new Order("O2", "Order 2", customer2, contract2, orderLines2);
//
//        service.addOrder(o1);
//        service.addOrder(o2);

        var allOrders = service.findAllOrders();

        StringBuilder sb = new StringBuilder("All Orders in Repository:<br>");
        allOrders.forEach(order -> sb.append(order).append("<br>"));

        return sb.toString();
    }
}
