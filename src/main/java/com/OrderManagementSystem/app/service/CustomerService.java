package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public void addCustomer(Customer customer) {
        repo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer getCustomerById(String id) {
        return repo.findById(id);
    }

    public void deleteCustomer(String id) {
        repo.delete(id);
    }
}
