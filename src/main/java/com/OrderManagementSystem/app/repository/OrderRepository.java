package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InFileRepo<Order> {
    public OrderRepository() {
        super("order.json", Order.class);
    }
}
