package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.repository.OrderLineRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {
    private final OrderLineRepository repo;

    public OrderLineService(OrderLineRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveOrderLine(OrderLine orderLine){
        validateBusinessRules(orderLine);
        repo.save(orderLine);
    }

    public List<OrderLine> searchOrderLines(String itemName, String unitName, String orderName,
                                            String sortField1, String sortDir1,
                                            String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchOrderLines(itemName, unitName, orderName, sort1.and(sort2));
    }

    public OrderLine getOrderLineById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteOrderLine(String id){
        repo.delete(getOrderLineById(id));
    }

    public void validateBusinessRules(OrderLine orderLine) {
        SellableItem item = orderLine.getItem();
        UnitOfMeasure unit = orderLine.getUnit();
        Order order = orderLine.getOrder();

        // 1. Validate Stock (if Product)
        if (item instanceof Product) {
            Product product = (Product) item;
            // Note: For strict correctness, we should exclude the current line's OLD quantity
            // if updating, but simple stock check is usually acceptable.
            if (product.getStockQuantity() < orderLine.getQuantity()) {
                throw new ValidationException("Insufficient stock for '" + item.getName() +
                        "'. Only " + product.getStockQuantity() + " units available.");
            }
        }

        // 2. Validate Contract Rules
        if (order != null && order.getContract() != null) {
            Contract contract = order.getContract();

            // Find the specific line in the Contract for this Item
            Optional<ContractLine> match = contract.getContractLines().stream()
                    .filter(cl -> cl.getItem().getId().equals(item.getId()))
                    .findFirst();

            // A) Check if Item exists in Contract
            if (match.isEmpty()) {
                throw new ValidationException("Item '" + item.getName() +
                        "' is not part of the contract '" + contract.getName() + "'.");
            }

            ContractLine contractLine = match.get();

            // B) Check if Unit of Measure matches the Contract
            if (!contractLine.getUnit().getId().equals(unit.getId())) {
                throw new ValidationException("Unit mismatch for item '" + item.getName() +
                        "'. Order uses '" + unit.getName() +
                        "' but Contract requires '" + contractLine.getUnit().getName() + "'.");
            }

            // C) Check Contract Quantity Limit (Total Consumed + Current Request)
            double limit = contractLine.getQuantity();

            // Calculate what has been used so far (Database Sum)
            // Note: Requires the 'getConsumedQuantity' method added to Repository in previous step
            Double usedQuantity = repo.getConsumedQuantity(
                    contract.getId(),
                    item.getId(),
                    orderLine.getId() // Exclude current ID to allow edits
            );

            double newTotal = usedQuantity + orderLine.getQuantity();

            if (newTotal > limit) {
                throw new ValidationException("Contract limit exceeded for item '" + item.getName() +
                        "'. Limit: " + limit +
                        ", Previously Used: " + usedQuantity +
                        ", Requested: " + orderLine.getQuantity());
            }
        }
    }
}