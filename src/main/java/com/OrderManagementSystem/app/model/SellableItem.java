package com.OrderManagementSystem.app.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Product.class, name = "product"),
        @JsonSubTypes.Type(value = ServiceEntity.class, name = "service")
})


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sellable_item")
public abstract class SellableItem implements ModelInterface{
    @Id
    @Column(length = 36, columnDefinition = "VARCHAR(36)")
    protected String id;

    @Column(length = 255, columnDefinition = "VARCHAR(255)")
    protected String name;

    public SellableItem(String name) {
        this.name = name;
    }

    public SellableItem() {}

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SellableItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

