package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Order;

import java.util.ArrayList;
import java.util.Map;

public class OrderRepository {
    private Map<String, Order> orders;

    public OrderRepository(Map<String, Order> orders) {
        this.orders = orders;
    }

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
