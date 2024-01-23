package com.nicu.customer_service.repositories;

import com.nicu.customer_service.domain.Address;
import com.nicu.customer_service.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
