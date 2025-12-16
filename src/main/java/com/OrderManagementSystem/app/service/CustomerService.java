package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Customer;
import com.OrderManagementSystem.app.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<Customer> searchCustomers(String name, String email, String currency,
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

        return repo.searchCustomers(name, email, currency, sort);
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
