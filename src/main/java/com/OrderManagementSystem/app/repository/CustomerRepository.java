package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE " +
            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:currency IS NULL OR LOWER(c.currency) LIKE LOWER(CONCAT('%', :currency, '%')))")
    List<Customer> searchCustomers(@Param("name") String name,
                                   @Param("email") String email,
                                   @Param("currency") String currency,
                                   Sort sort);
}
