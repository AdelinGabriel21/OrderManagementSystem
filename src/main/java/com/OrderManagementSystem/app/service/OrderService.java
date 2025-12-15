package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.model.Order;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.OrderRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;

    @Autowired
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveOrder(Order order) {
        validateBusinessRules(order);
        repo.save(order);
    }

    public List<Order> searchOrders(String name, String customerName, String contractName,
                                    String sortField1, String sortDir1,
                                    String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchOrders(name, customerName, contractName, sort1.and(sort2));
    }

    public Order findOrderById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteOrder(String id) {
        repo.delete(findOrderById(id));
    }


    public void validateBusinessRules(Order order) {
        Customer customer = order.getCustomer();
        Contract contract = order.getContract();

        if (customer == null || contract == null) {
            return;
        }

        Customer contractCustomer = contract.getCustomer();

        if (contractCustomer != null && !contractCustomer.getId().equals(customer.getId())) {
            throw new ValidationException("The selected contract does not belong to the selected customer.");
        }

        if (contract.getStatus() != Status.ACTIVE) {
            throw new ValidationException("The selected contract is '" + contract.getStatus() + "' and cannot be used.");
        }

        Date now = new Date();
        if (contract.getExpirationDate() != null && contract.getExpirationDate().before(now)) {
            throw new ValidationException("The selected contract has expired.");
        }
    }
}
