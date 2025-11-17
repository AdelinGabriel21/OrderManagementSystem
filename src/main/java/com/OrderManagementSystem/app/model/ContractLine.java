package com.OrderManagementSystem.app.model;

public class ContractLine implements ModelInterface {
    private String id;
    private SellableItem item;
    private UnitOfMeasure unit;
    private double quantity;

    public ContractLine(SellableItem item, UnitOfMeasure unit, double quantity) {
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    @Override
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

    @Override
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
        return "ContractLine{" +
                "id='" + id + '\'' +
                ", item=" + item +
                ", unit=" + unit +
                ", quantity=" + quantity +
                '}';
    }
}
