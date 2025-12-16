package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> searchProducts(String name, Double minValue, Double maxValue,
                                        String sortField1, String sortDir1,
                                        String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchProducts(name, minValue, maxValue, sort1.and(sort2));
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