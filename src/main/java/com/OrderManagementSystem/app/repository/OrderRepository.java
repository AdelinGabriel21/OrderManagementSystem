package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public ArrayList<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Order findById(String id) {
        return orders.get(id);
    }

    public void delete(String orderID) {
        orders.remove(orderID);
    }
}
