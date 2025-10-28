package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/test-products")
    @ResponseBody
    public String testProducts() {
        Product p1 = new Product("1", "Water", 3, 100);
        Product p2 = new Product("2", "Juice", 4, 50);
        Product p3 = new Product("3", "Milk", 2, 80);

        service.addProduct(p1);
        service.addProduct(p2);
        service.addProduct(p3);

        List<Product> allProducts = service.getAllProducts();

        StringBuilder sb = new StringBuilder("All products in repository:<br>");
        allProducts.forEach(product -> sb.append(product).append("<br>"));

        Product fetched = service.getProductById("2");
        sb.append("<br>Fetched product with ID 2: ").append(fetched).append("<br>");

        service.deleteProduct("3");
        sb.append("<br>After deleting product with ID 3:<br>");
        service.getAllProducts().forEach(product -> sb.append(product).append("<br>"));

        sb.append("<br>ProductService test completed.");

        return sb.toString();
    }
}
