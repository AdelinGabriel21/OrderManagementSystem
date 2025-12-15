package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.UnitOfMeasure;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, String> {
    @Query("SELECT u FROM UnitOfMeasure u WHERE " +
            "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:symbol IS NULL OR LOWER(u.symbol) LIKE LOWER(CONCAT('%', :symbol, '%')))")
    List<UnitOfMeasure> searchUnits(@Param("name") String name,
                                    @Param("symbol") String symbol,
                                    Sort sort);
}
