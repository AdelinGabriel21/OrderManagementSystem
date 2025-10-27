package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Service;
import java.util.ArrayList;
import java.util.Map;

public class ServiceRepository {
    private Map<String, Service> services;

    public ServiceRepository(Map<String, Service> services) {
        this.services = services;
    }

    public void save(Service service){
        services.put(service.getId(), service);
    }

    public ArrayList<Service> findAll(){
        return new ArrayList<>(services.values());
    }

    public Service finByID(String id){
        return services.get(id);
    }

    public void delete(String serviceId){
        services.remove(serviceId);
    }
}
