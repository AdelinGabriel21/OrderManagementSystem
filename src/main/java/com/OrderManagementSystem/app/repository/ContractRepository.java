package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Contract;
import com.OrderManagementSystem.app.model.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query("SELECT c FROM Contract c WHERE " +
            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:status IS NULL OR c.status = :status) AND " +
            "(:fromDate IS NULL OR c.creationDate >= :fromDate) AND " +
            "(:toDate IS NULL OR c.creationDate <= :toDate)")
    List<Contract> searchContracts(@Param("name") String name,
                                   @Param("status") Status status,
                                   @Param("fromDate") Date fromDate,
                                   @Param("toDate") Date toDate,
                                   Sort sort);
}
