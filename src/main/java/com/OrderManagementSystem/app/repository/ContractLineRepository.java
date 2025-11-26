package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractLineRepository extends JpaRepository<ContractLine, String> {
}
