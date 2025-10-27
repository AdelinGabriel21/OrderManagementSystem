package com.OrderManagementSystem.app.model;

public enum Type {
    CUSTOMER("Customer"), SELLER("Seller");
    private String value;

    Type(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Type{" +
                "value='" + value + '\'' +
                '}';
    }
}
