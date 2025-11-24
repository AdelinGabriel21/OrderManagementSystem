package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer implements ModelInterface {
    @Id
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(length = 10, columnDefinition = "VARCHAR(10)")
    private String currency;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "phone_number", length = 50, columnDefinition = "VARCHAR(50)")
    private String phoneNumber;

    public Customer(String name, String currency, List<Order> orders, List<Contract> contracts) {
        this.name = name;
        this.currency = currency;
        this.orders = orders;
        this.contracts = contracts;
    }

    public Customer() {}

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", orders=" + orders +
                ", contracts=" + contracts +
                '}';
    }
}
