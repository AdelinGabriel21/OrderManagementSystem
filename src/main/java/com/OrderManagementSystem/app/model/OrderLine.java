package com.OrderManagementSystem.app.model;

public class OrderLine {
    String id;
    SellableItem item;
    UnitOfMeasure unit;
    double quantity;

    OrderLine(String id, SellableItem item, UnitOfMeasure unit, double quantity){
        this.id = id;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public SellableItem getItem() {
        return item;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(SellableItem item) {
        this.item = item;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id='" + id + '\'' +
                ", item=" + item +
                ", unit=" + unit +
                ", quantity=" + quantity +
                '}';
    }
}
