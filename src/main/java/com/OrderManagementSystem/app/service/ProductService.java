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
        return repo.findById(id).orElse(null);
    }

    public void deleteProduct(String id){
        repo.delete(getProductById(id));
    }


}
