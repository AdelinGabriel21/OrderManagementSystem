package com.OrderManagementSystem.app.model;

public class ServiceEntity extends SellableItem {
    private Status status;

    public ServiceEntity(String name, Status status) {
        super(name);
        this.status = status;
    }

    public ServiceEntity() {}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                "status=" + status +
                '}';
    }
}
