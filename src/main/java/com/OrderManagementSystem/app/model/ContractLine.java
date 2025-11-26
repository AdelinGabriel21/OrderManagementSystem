package com.OrderManagementSystem.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contract_lines")
public class ContractLine implements ModelInterface {
    @Id
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "item_id", columnDefinition = "VARCHAR(36)")
    private SellableItem item;

    @ManyToOne
    @JoinColumn(name = "unit_id", columnDefinition = "VARCHAR(36)")
    private UnitOfMeasure unit;

    @Column(columnDefinition = "DOUBLE")
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "contract_id", columnDefinition = "VARCHAR(36)")
    private Contract contract;

    public ContractLine(SellableItem item, UnitOfMeasure unit, double quantity) {
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public ContractLine() {}

    @Override
    public String getId() {
        return id;
    }

    public SellableItem getItem() {
        return item;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public Contract getContract() {
        return contract;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setItem(SellableItem item) {
        this.item = item;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "ContractLine{" +
                "id='" + id + '\'' +
                ", item=" + item +
                ", unit=" + unit +
                ", quantity=" + quantity +
                '}';
    }
}
