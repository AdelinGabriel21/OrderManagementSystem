package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void addProduct(Product product){
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
}
