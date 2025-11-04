package com.OrderManagementSystem.app.model;

public class Product extends SellableItem {
    private double value;
    private int stockQuantity;

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

    public void setStockQuantity(int sotckQuantity){
        this.stockQuantity = stockQuantity;
    }

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
