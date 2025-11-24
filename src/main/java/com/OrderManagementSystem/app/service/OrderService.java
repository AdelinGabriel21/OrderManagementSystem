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

    public void saveOrder(Order order) {
        repo.save(order);
    }

    public List<Order> findAllOrders() {
        return repo.findAll();
    }

    public Order findOrderById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteOrder(String id) {
        repo.delete(findOrderById(id));
    }


}
