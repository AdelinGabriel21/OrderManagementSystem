package com.OrderManagementSystem.app.model;

public class UnitOfMeasure implements ModelInterface {
    private String id;
    private String name;
    private String symbol;

    public UnitOfMeasure(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public UnitOfMeasure() {}

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
