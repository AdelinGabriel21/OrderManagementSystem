package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public void save(Product product){
        products.put(product.getId(), product);
    }

    public ArrayList<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public Product findById(String id){
        return products.get(id);
    }

    public void delete(String productID){
        products.remove(productID);
    }
}
