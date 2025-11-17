package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InFileRepo<Product> {
    public ProductRepository() {
        super("products.json", Product.class);
    }
}
