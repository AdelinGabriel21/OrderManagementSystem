package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contract_type")
public class ContractType implements ModelInterface {
    @Id
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, columnDefinition = "VARCHAR(50)")
    private Type type;

    public ContractType(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public ContractType() {}

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
