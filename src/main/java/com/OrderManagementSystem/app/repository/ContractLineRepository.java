package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ContractLine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractLineRepository extends JpaRepository<ContractLine, String> {
    @Query("SELECT cl FROM ContractLine cl WHERE " +
            "(:contractName IS NULL OR LOWER(cl.contract.name) LIKE LOWER(CONCAT('%', :contractName, '%'))) AND " +
            "(:itemName IS NULL OR LOWER(cl.item.name) LIKE LOWER(CONCAT('%', :itemName, '%'))) AND " +
            "(:unitName IS NULL OR LOWER(cl.unit.name) LIKE LOWER(CONCAT('%', :unitName, '%')))")
    List<ContractLine> searchContractLines(@Param("contractName") String contractName,
                                           @Param("itemName") String itemName,
                                           @Param("unitName") String unitName,
                                           Sort sort);
}
