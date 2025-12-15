package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:minValue IS NULL OR p.value >= :minValue) AND " +
            "(:maxValue IS NULL OR p.value <= :maxValue)")
    List<Product> searchProducts(@Param("name") String name,
                                 @Param("minValue") Double minValue,
                                 @Param("maxValue") Double maxValue,
                                 Sort sort);
}
