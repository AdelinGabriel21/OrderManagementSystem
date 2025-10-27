package com.OrderManagementSystem.app.model;

public abstract class SellableItem {
    protected String id;
    protected String name;

    public SellableItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

