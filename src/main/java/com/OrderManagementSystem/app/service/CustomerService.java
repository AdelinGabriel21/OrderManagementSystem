package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public void saveCustomer(Customer customer) {
        repo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer getCustomerById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteCustomer(String id) {
        repo.delete(getCustomerById(id));
    }


}
