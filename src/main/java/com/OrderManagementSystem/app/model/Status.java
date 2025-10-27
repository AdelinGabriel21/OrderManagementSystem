package com.OrderManagementSystem.app.model;

public enum Status{
    ACTIVE("Active"), DOWN("Down");
    private String value;

    Status(String value){
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
        return "Status{" +
                "value='" + value + '\'' +
                '}';
    }
}
