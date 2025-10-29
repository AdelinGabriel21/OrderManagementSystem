package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Order;
import com.OrderManagementSystem.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
}
