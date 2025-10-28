package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Customer;
import java.util.ArrayList;
import java.util.Map;

public class CustomerRepository {
    private Map<String, Customer> customers;

    public CustomerRepository(Map<String, Customer> customers) {
        this.customers = customers;
    }

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
