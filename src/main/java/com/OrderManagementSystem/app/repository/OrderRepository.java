package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT o FROM Order o WHERE " +
            "(:name IS NULL OR LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:customerName IS NULL OR LOWER(o.customer.name) LIKE LOWER(CONCAT('%', :customerName, '%'))) AND " +
            "(:contractName IS NULL OR LOWER(o.contract.name) LIKE LOWER(CONCAT('%', :contractName, '%')))")
    List<Order> searchOrders(@Param("name") String name,
                             @Param("customerName") String customerName,
                             @Param("contractName") String contractName,
                             Sort sort);
}
