package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.model.Order;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public void addOrder(Order order) {
        repo.save(order);
    }

    public List<Order> findAllOrders() {
        return repo.findAll();
    }

    public Order findOrderById(String id) {
        return repo.findById(id);
    }

    public void deleteOrder(String id) {
        repo.delete(id);
    }

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            Customer c1 = new Customer("Adelin Cracea", "EUR", new ArrayList<>(), new ArrayList<>());
            Customer c2 = new Customer("TechWorks GmbH", "USD", new ArrayList<>(), new ArrayList<>());

            Contract contract1 = new Contract("Contract 1", "Type1", Status.ACTIVE, Collections.emptyList(), new Date(), new Date());
            Contract contract2 = new Contract("Contract 2", "Type2", Status.DOWN, Collections.emptyList(), new Date(), new Date());

            Order o1 = new Order("Order A", c1, contract1, new ArrayList<>());
            Order o2 = new Order("Order B", c2, contract2, new ArrayList<>());

            addOrder(o1);
            addOrder(o2);
        }
    }
}
