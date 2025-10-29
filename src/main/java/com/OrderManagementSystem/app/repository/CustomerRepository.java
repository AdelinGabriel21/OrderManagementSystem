package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InMemoryRepo<Customer> {
}
