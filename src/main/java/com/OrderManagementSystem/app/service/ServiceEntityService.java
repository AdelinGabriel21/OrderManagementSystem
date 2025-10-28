package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.repository.ServiceEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntityService {
    private final ServiceEntityRepository repo;

    public ServiceEntityService(ServiceEntityRepository repo) {
        this.repo = repo;
    }

    public void addService(ServiceEntity service){
        repo.save(service);
    }

    public List<ServiceEntity> getAllServices(){
        return repo.findAll();
    }

    public ServiceEntity getServiceById(String id){
        return repo.findById(id);
    }

    public void deleteService(String id){
        repo.delete(id);
    }
}
