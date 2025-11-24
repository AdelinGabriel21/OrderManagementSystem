package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.OrderLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.SellableItem;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.repository.OrderLineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {
    private final OrderLineRepository repo;

    public OrderLineService(OrderLineRepository repo) {
        this.repo = repo;
    }

    public void saveOrderLine(OrderLine orderLine){
        repo.save(orderLine);
    }

    public List<OrderLine> getAllOrderLines(){
        return repo.findAll();
    }

    public OrderLine getOrderLineById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteOrderLine(String id){
        repo.delete(getOrderLineById(id));
    }



}
