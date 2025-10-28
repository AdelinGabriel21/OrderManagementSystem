package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.model.Order;
import com.OrderManagementSystem.app.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/test-customers")
    @ResponseBody
    public String testCustomers() {
        List<Order> orders1 = new ArrayList<>();
        List<Order> orders2 = new ArrayList<>();

        List<Contract> contracts1 = new ArrayList<>();
        List<Contract> contracts2 = new ArrayList<>();

        Customer c1 = new Customer("C1", "Adelin Cracea", "EUR", orders1, contracts1);
        c1.setEmail("adelin@example.com");
        c1.setPhoneNumber("+40773829445");

        Customer c2 = new Customer("C2", "TechWorks GmbH", "USD", orders2, contracts2);
        c2.setEmail("contact@techworks.com");
        c2.setPhoneNumber("+49030123456");

        service.addCustomer(c1);
        service.addCustomer(c2);

        var allCustomers = service.getAllCustomers();

        StringBuilder sb = new StringBuilder("All Customers in Repository:<br>");
        allCustomers.forEach(customer -> sb.append(customer).append("<br>"));

        return sb.toString();
    }
}
