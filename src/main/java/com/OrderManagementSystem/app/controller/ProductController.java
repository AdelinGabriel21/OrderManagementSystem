package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.service.ProductService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void testAll(){
        Product p1 = new Product("1", "Water", 3, 100);
        Product p2 = new Product("2", "Juice", 4, 50);
        Product p3 = new Product("3", "Milk", 2, 80);
        service.addProduct(p1);
        service.addProduct(p2);
        service.addProduct(p3);

        List<Product> allProducts = service.getAllProducts();
        System.out.println("All products in repository:");
        for (Product p : allProducts) {
            System.out.println(p);
        }

        Product fetched = service.getProductById("2");
        System.out.println("Fetched product with ID 2: " + fetched);

        service.deleteProduct("3");
        System.out.println("After deleting ID 3:");
        service.getAllProducts().forEach(System.out::println);

        System.out.println("ProductService test completed.");
    }
}
