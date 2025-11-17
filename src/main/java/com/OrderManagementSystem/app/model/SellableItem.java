package com.OrderManagementSystem.app.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Product.class, name = "product"),
        @JsonSubTypes.Type(value = ServiceEntity.class, name = "service")
})

public abstract class SellableItem implements ModelInterface{
    protected String id;
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

