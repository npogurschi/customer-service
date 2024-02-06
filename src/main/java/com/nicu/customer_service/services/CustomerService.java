package com.nicu.customer_service.services;


import com.nicu.customer_service.model.Customer;

import java.util.List;


public interface CustomerService {

    List<Customer> findAll();

    Customer findCustomer(Long id);

    List<Customer> findCustomersByFirstNameOrLastName(String firstName, String lastName);

    String createCustomer(Customer customer);

    String updateCustomer(Customer customer);

    Iterable<Customer> findAllTemplate();
}
