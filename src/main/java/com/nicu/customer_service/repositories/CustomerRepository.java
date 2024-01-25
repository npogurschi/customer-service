package com.nicu.customer_service.repositories;

import com.nicu.customer_service.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerByFirstNameOrLastName(String firstName, String lastName);
}

