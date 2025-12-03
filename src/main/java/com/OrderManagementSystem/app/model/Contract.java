package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contracts")
public class  Contract implements ModelInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @NotBlank(message = "Name is required.")
    @Column(nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @NotNull(message = "Contract Type is required.")
    @ManyToOne
    @JoinColumn(name = "contract_type_id", nullable = false)
    private ContractType contractType;

    @NotNull(message = "Status is required.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private Status status;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractLine> contractLines;

    @PastOrPresent(message = "Creation date cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", columnDefinition = "DATE")
    private Date creationDate;

    @Future(message = "Expiration date must be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date", columnDefinition = "DATE")
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", columnDefinition = "VARCHAR(36)")
    private Customer customer;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public Contract(String name, ContractType contractType, Status status, Date creationDate, Date expirationDate) {
        this.name = name;
        this.contractType = contractType;
        this.status = status;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public Contract() {}

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public Status getStatus() {
        return status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    public Date getCreationDate(){
        return creationDate;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setContractLines(List<ContractLine> contractLines) {
        this.contractLines = contractLines;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ContractTypeId='" + contractType + '\'' +
                ", status=" + status +
                ", contracts=" + contractLines +
                '}';
    }
}