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

        Sort sort = Sort.unsorted();

        if (sortField1 != null && !sortField1.equals("none")) {
            Sort s1 = sortDir1.equalsIgnoreCase("asc") ?
                    Sort.by(sortField1).ascending() :
                    Sort.by(sortField1).descending();
            sort = s1;
        }

        if (sortField2 != null && !sortField2.equals("none")) {
            Sort s2 = sortDir2.equalsIgnoreCase("asc") ?
                    Sort.by(sortField2).ascending() :
                    Sort.by(sortField2).descending();

            if (sort.isSorted()) {
                sort = sort.and(s2);
            } else {
                sort = s2;
            }
        }

        return repo.searchServices(name, status, sort);
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