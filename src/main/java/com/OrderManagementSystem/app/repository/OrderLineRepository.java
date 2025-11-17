package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.OrderLine;
import org.springframework.stereotype.Repository;

@Repository
public class OrderLineRepository extends InFileRepo<OrderLine>{
    public OrderLineRepository (){
        super("orderLine.json", OrderLine.class);
    }
}
