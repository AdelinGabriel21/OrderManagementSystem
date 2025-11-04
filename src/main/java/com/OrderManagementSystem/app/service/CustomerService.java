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

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            Customer c1 = new Customer("Adelin Cracea", "EUR", new ArrayList<>(), new ArrayList<>());
            c1.setEmail("adelin@example.com");
            c1.setPhoneNumber("+40773829445");

            Customer c2 = new Customer("TechWorks GmbH", "USD", new ArrayList<>(), new ArrayList<>());
            c2.setEmail("contact@techworks.com");
            c2.setPhoneNumber("+49030123456");

            Customer c3 = new Customer("Global Supplies SRL", "RON", new ArrayList<>(), new ArrayList<>());
            c3.setEmail("info@globalsupplies.ro");
            c3.setPhoneNumber("+40213123456");

            addCustomer(c1);
            addCustomer(c2);
            addCustomer(c3);
        }
    }
}
