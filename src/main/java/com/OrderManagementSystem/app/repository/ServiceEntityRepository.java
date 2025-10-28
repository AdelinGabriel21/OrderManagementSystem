package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ServiceEntity;

import java.util.ArrayList;
import java.util.Map;

public class ServiceEntityRepository {
    private Map<String, ServiceEntity> services;

    public ServiceEntityRepository(Map<String, ServiceEntity> services) {
        this.services = services;
    }

    public void save(ServiceEntity service){
        services.put(service.getId(), service);
    }

    public ArrayList<ServiceEntity> findAll(){
        return new ArrayList<>(services.values());
    }

    public ServiceEntity findById(String id){
        return services.get(id);
    }

    public void delete(String serviceId){
        services.remove(serviceId);
    }
}
