package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public ArrayList<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public Customer findById(String id) {
        return customers.get(id);
    }

    public void delete(String customerID) {
        customers.remove(customerID);
    }
}
