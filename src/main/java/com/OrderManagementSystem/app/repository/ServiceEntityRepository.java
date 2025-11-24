package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, String> {
}
