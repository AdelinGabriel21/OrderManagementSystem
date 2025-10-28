package com.OrderManagementSystem.app.model;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String currency;
    private List<Order> orders;
    private List<Contract> contracts;
    private String email;
    private String phoneNumber;

    public Customer(String id, String name, String currency, List<Order> orders, List<Contract> contracts) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.orders = orders;
        this.contracts = contracts;
    }

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
