package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Product;
import java.util.ArrayList;
import java.util.Map;

public class ProductRepository {
    private Map<String, Product> products;

    public ProductRepository(Map<String, Product> products) {
        this.products = products;
    }

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
