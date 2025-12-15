package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.OrderLine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, String> {
    @Query("SELECT ol FROM OrderLine ol WHERE " +
            "(:itemName IS NULL OR LOWER(ol.item.name) LIKE LOWER(CONCAT('%', :itemName, '%'))) AND " +
            "(:unitName IS NULL OR LOWER(ol.unit.name) LIKE LOWER(CONCAT('%', :unitName, '%'))) AND " +
            "(:orderName IS NULL OR LOWER(ol.order.name) LIKE LOWER(CONCAT('%', :orderName, '%')))")
    List<OrderLine> searchOrderLines(@Param("itemName") String itemName,
                                     @Param("unitName") String unitName,
                                     @Param("orderName") String orderName,
                                     Sort sort);
}
