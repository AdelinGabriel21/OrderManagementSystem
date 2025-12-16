package com.OrderManagementSystem.app.service;

import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.repository.ServiceEntityRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntityService {
    private final ServiceEntityRepository repo;

    public ServiceEntityService(ServiceEntityRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveService(ServiceEntity service){
        validateBusinessRules(service);
        repo.save(service);
    }

    public List<ServiceEntity> searchServices(String name, Status status,
                                              String sortField1, String sortDir1,
                                              String sortField2, String sortDir2) {

        Sort sort1 = sortDir1.equalsIgnoreCase("asc") ?
                Sort.by(sortField1).ascending() :
                Sort.by(sortField1).descending();

        Sort sort2 = sortDir2.equalsIgnoreCase("asc") ?
                Sort.by(sortField2).ascending() :
                Sort.by(sortField2).descending();

        return repo.searchServices(name, status, sort1.and(sort2));
    }

    public ServiceEntity getServiceById(String id){
        return repo.findById(id).orElse(null);
    }

    public void deleteService(String id){
        repo.delete(getServiceById(id));
    }

    public void validateBusinessRules(ServiceEntity serviceEntity) {
        List<ServiceEntity> allServices = repo.findAll();

        boolean nameExists = allServices.stream()
                .anyMatch(existingService ->
                        existingService.getName().equalsIgnoreCase(serviceEntity.getName()) &&
                                (serviceEntity.getId() == null || !existingService.getId().equals(serviceEntity.getId()))
                );

        if (nameExists) {
            throw new ValidationException("A service with the name '" + serviceEntity.getName() + "' already exists.");
        }
    }
}