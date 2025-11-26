package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, String> {
}
