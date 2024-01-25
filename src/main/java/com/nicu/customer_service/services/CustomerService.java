package com.nicu.customer_service.services;


import com.nicu.customer_service.domain.Customer;

import java.util.List;


public interface CustomerService {

    List<Customer> findAll();

    Customer findCustomer(Long id);

    Customer findCustomerByFirstNameOrLastName(String firstName, String lastName);

    void createCustomer(Customer customer);

    Iterable<Customer> findAllTemplate();
}
