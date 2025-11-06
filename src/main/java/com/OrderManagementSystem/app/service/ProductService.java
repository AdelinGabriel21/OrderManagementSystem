package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void saveProduct(Product product){
        repo.save(product);
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProductById(String id){
        return repo.findById(id);
    }

    public void deleteProduct(String id){
        repo.delete(id);
    }

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            Product p1 = new Product(
                    "Laptop",
                    1200.00,
                    15
            );

            Product p2 = new Product(
                    "Smartphone",
                    800.00,
                    30
            );

            Product p3 = new Product(
                    "Headphones",
                    150.00,
                    50
            );

            repo.save(p1);
            repo.save(p2);
            repo.save(p3);
        }
    }

}
