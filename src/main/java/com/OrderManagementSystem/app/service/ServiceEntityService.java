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

    @PostConstruct
    public void initData() {
        if (repo.findAll().isEmpty()) {
            ServiceEntity s1 = new ServiceEntity(
                    "Cleaning Service",
                    Status.ACTIVE
            );

            ServiceEntity s2 = new ServiceEntity(
                    "Cooking Service",
                    Status.DOWN
            );

            ServiceEntity s3 = new ServiceEntity(
                    "Driving Service",
                    Status.ACTIVE
            );

            repo.save(s1);
            repo.save(s2);
            repo.save(s3);
        }
    }

}
