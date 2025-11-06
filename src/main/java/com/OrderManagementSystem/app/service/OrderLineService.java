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
        return repo.findById(id);
    }

    public void deleteOrderLine(String id){
        repo.delete(id);
    }

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {

            SellableItem item1 = new Product("Laptop", 1200.00, 10);
            SellableItem item2 = new Product("Headphones", 100.00, 10);
            SellableItem item3 = new Product("Smartphone", 1000.00, 10);

            UnitOfMeasure u1 = new UnitOfMeasure("Kilogram", "kg");
            UnitOfMeasure u2 = new UnitOfMeasure("Meter", "m");
            UnitOfMeasure u3 = new UnitOfMeasure("Liter", "L");

            OrderLine ol1 = new OrderLine(
                    item1,
                    u1,
                    2
            );

            OrderLine ol2 = new OrderLine(
                    item2,
                    u2,
                    5
            );

            OrderLine ol3 = new OrderLine(
                    item3,
                    u3,
                    10
            );


            repo.save(ol1);
            repo.save(ol2);
            repo.save(ol3);
        }
    }

    public List<UnitOfMeasure> getAllUnits() {
        return List.of(
                new UnitOfMeasure("Kilogram", "kg"),
                new UnitOfMeasure("Meter", "m"),
                new UnitOfMeasure("Liter", "L")
        );
    }

    public List<SellableItem> getAllSellableItems() {
        return List.of(
                new Product("Laptop", 1200.00, 10),
                new Product("Headphones", 100.00, 10),
                new Product("Smartphone", 1000.00, 10)
        );
    }

}
