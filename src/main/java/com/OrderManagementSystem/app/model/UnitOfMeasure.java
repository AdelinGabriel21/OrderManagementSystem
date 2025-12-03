package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class UnitOfMeasure implements ModelInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank(message = "Name is required.")
    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @NotBlank(message = "Symbol is required.")
    @Size(max = 10, message = "Symbol cannot exceed 10 characters.")
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
