package com.OrderManagementSystem.app.model;

public class Product extends SellableItem{
    private double value;

    Product(String id, String name, double value){
        super(id,name);
        this.value = value;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                "value=" + value +
                '}';
    }
}
