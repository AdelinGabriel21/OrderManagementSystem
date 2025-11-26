package com.OrderManagementSystem.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UnitOfMeasure implements ModelInterface {
    @Id
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(length = 10, columnDefinition = "VARCHAR(10)")
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
