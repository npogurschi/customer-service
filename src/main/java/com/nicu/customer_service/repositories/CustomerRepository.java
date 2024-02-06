package com.nicu.customer_service.repositories;

import com.nicu.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Customer> findByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}

