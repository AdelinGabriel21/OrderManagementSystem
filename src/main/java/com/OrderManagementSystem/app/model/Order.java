package com.OrderManagementSystem.app.model;

import java.util.List;

public class Order {
    private String id;
    private String name;
    private Customer customer;
    private Contract contract;
    private List<OrderLine> orderLines;

    public Order(String id, String name, Customer customer, Contract contract, List<OrderLine> orderLines) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.contract = contract;
        this.orderLines = orderLines;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Contract getContract() {
        return contract;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", customer=" + customer +
                ", contract=" + contract +
                ", orderLines=" + orderLines +
                '}';
    }
}
