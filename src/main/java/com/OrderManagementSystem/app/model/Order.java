package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements ModelInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank(message = "Order name is required.")
    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id", columnDefinition = "VARCHAR(36)")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "contract_id", columnDefinition = "VARCHAR(36)")
    private Contract contract;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines;

    public Order(String name, Customer customer, Contract contract, List<OrderLine> orderLines) {
        this.name = name;
        this.customer = customer;
        this.contract = contract;
        this.orderLines = orderLines;
    }

    public Order(){}

    @Override
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

    @Override
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
