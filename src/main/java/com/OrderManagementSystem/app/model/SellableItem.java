package com.OrderManagementSystem.app.model;

public abstract class SellableItem implements ModelInterface{
    protected String id;
    protected String name;

    public SellableItem(String name) {
        this.name = name;
    }

    public SellableItem() {}

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SellableItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

