package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.model.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, String> {
    @Query("SELECT s FROM ServiceEntity s WHERE " +
            "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:status IS NULL OR s.status = :status)")
    List<ServiceEntity> searchServices(@Param("name") String name,
                                       @Param("status") Status status,
                                       Sort sort);
}
