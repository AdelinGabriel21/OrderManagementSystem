package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        validateBusinessRules(customer);
        repo.save(customer);
    }

    public List<Customer> getAllCustomers(String sortField1, String sortDir1,
                                          String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.findAll(sort1.and(sort2));
    }
    public Customer getCustomerById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteCustomer(String id) {
        repo.delete(getCustomerById(id));
    }

    public void validateBusinessRules(Customer customer) {
        List<Customer> allCustomers = repo.findAll();

        boolean emailExists = allCustomers.stream()
                .anyMatch(existing ->
                        existing.getEmail().equalsIgnoreCase(customer.getEmail()) &&
                                (customer.getId() == null || !existing.getId().equals(customer.getId()))
                );

        if (emailExists) {
            throw new ValidationException("A customer with the email '" + customer.getEmail() + "' already exists.");
        }

        boolean phoneExists = allCustomers.stream()
                .anyMatch(existing ->
                        existing.getPhoneNumber().equals(customer.getPhoneNumber()) &&
                                (customer.getId() == null || !existing.getId().equals(customer.getId()))
                );

        if (phoneExists) {
            throw new ValidationException("A customer with the phone number '" + customer.getPhoneNumber() + "' already exists.");
        }
    }
}
