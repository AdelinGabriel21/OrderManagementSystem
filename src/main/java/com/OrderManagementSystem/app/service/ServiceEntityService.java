package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.ServiceEntityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntityService {
    private final ServiceEntityRepository repo;

    public ServiceEntityService(ServiceEntityRepository repo) {
        this.repo = repo;
    }

    public void saveService(ServiceEntity service){
        repo.save(service);
    }

    public List<ServiceEntity> getAllServices(){
        return repo.findAll();
    }

    public ServiceEntity getServiceById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteService(String id){
        repo.delete(getServiceById(id));
    }


}
