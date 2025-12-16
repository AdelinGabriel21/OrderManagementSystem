package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Size(max = 100, message = "Contract name cannot exceed 100 characters.")
    @Column(nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @NotBlank(message = "Contract Type ID is required.")
    @Column(name = "contract_type_id", nullable = false, length = 36, columnDefinition = "VARCHAR(36)")
    private String contractTypeId;

    @NotNull(message = "Status is required.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private Status status;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractLine> contractLines;

    @NotNull(message = "Creation date is required.")
    @PastOrPresent(message = "Creation date cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", columnDefinition = "DATE")
    private Date creationDate;

    @NotNull(message = "Expiration date is required.")
    @Future(message = "Expiration date must be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date", columnDefinition = "DATE")
    private Date expirationDate;

    @NotNull(message = "Customer is required.")
    @ManyToOne
    @JoinColumn(name = "customer_id", columnDefinition = "VARCHAR(36)")
    private Customer customer;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public Contract(String name, String contractTypeId, Status status, List<ContractLine> contracts,
                    Date creationDate, Date expirationDate) {
        this.name = name;
        this.contractTypeId = contractTypeId;
        this.status = status;
        this.contractLines = contracts;
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

    public String getContractTypeId() {
        return contractTypeId;
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

    public void setContractTypeId(String contractTypeId) {
        this.contractTypeId = contractTypeId;
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
                ", status=" + status +
                '}';
    }
}