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

    public List<OrderLine> searchOrderLines(String itemName, String orderName, String unitName,
                                            String sortField1, String sortDir1,
                                            String sortField2, String sortDir2) {

        Sort sort = Sort.unsorted();

        if (sortField1 != null && !sortField1.equals("none")) {
            Sort s1 = sortDir1.equalsIgnoreCase("asc") ?
                    Sort.by(sortField1).ascending() :
                    Sort.by(sortField1).descending();
            sort = s1;
        }

        if (sortField2 != null && !sortField2.equals("none")) {
            Sort s2 = sortDir2.equalsIgnoreCase("asc") ?
                    Sort.by(sortField2).ascending() :
                    Sort.by(sortField2).descending();

            if (sort.isSorted()) {
                sort = sort.and(s2);
            } else {
                sort = s2;
            }
        }

        return repo.searchOrderLines(itemName, orderName, unitName, sort);
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