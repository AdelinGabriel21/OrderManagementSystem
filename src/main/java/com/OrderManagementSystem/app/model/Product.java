package com.OrderManagementSystem.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product extends SellableItem {
    @Positive(message = "Value must be positive.")
    @Column(columnDefinition = "DOUBLE")
    private double value;

    @Positive(message = "Stock cannot be negative.")
    @Column(name = "stock_quantity", columnDefinition = "INT")
    private int stockQuantity;

    private final String type = "product";

    public Product(String name, double value, int stockQuantity){
        super(name);
        this.value = value;
        this.stockQuantity = stockQuantity;
    }

    public Product() {}

    public double getValue(){
        return value;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }

    public void setValue(double value){
        this.value = value;
    }

    public void setStockQuantity(int stockQuantity){
        this.stockQuantity = stockQuantity;
    }

    public String getType() { return type; }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
