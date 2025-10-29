package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InMemoryRepo<Product> {
}
