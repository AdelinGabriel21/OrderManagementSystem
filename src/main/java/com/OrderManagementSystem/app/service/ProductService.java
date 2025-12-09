package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveProduct(Product product){
        validateBusinessRules(product);
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


    public void validateBusinessRules(Product product) {
        List<Product> allProducts = repo.findAll();

        boolean nameExists = allProducts.stream()
                .anyMatch(existingProduct ->
                        existingProduct.getName().equalsIgnoreCase(product.getName()) &&
                                (product.getId() == null || !existingProduct.getId().equals(product.getId()))
                );

        if (nameExists) {
            throw new ValidationException("A product with the name '" + product.getName() + "' already exists.");
        }
    }
}
