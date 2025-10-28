package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ServiceEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ServiceEntityRepository {
    private Map<String, ServiceEntity> services = new HashMap<>();

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
