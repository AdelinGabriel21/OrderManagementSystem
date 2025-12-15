package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.OrderLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.SellableItem;
import com.OrderManagementSystem.app.repository.OrderLineRepository;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {
    private final OrderLineRepository repo;

    public OrderLineService(OrderLineRepository repo) {
        this.repo = repo;
    }

    public void saveOrderLine(OrderLine orderLine){
        validateBusinessRules(orderLine);
        repo.save(orderLine);
    }

    public List<OrderLine> searchOrderLines(String itemName, String unitName, String orderName,
                                            String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        return repo.searchOrderLines(itemName, unitName, orderName, sort);
    }

    public OrderLine getOrderLineById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteOrderLine(String id){
        repo.delete(getOrderLineById(id));
    }

    public void validateBusinessRules(OrderLine orderLine) {
        SellableItem item = orderLine.getItem();

        if (item instanceof Product) {
            Product product = (Product) item;
            if (product.getStockQuantity() < orderLine.getQuantity()) {
                throw new ValidationException("Insufficient stock. Only " + product.getStockQuantity() + " units available.");
            }
        }
    }

}
