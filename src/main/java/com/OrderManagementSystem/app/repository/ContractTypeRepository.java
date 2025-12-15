package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractType;
import com.OrderManagementSystem.app.model.Type;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, String> {
    @Query("SELECT ct FROM ContractType ct WHERE " +
            "(:name IS NULL OR LOWER(ct.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:type IS NULL OR ct.type = :type)")
    List<ContractType> searchContractTypes(@Param("name") String name,
                                           @Param("type") Type type,
                                           Sort sort);
}
