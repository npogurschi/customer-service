package com.nicu.customer_service.services;


import com.nicu.customer_service.dto.CustomerDTO;
import com.nicu.customer_service.model.Customer;

import java.util.List;


public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findCustomer(Long id);

    List<CustomerDTO> findCustomersByFirstNameOrLastName(String firstName, String lastName);

    String createCustomer(CustomerDTO customer);

    String updateCustomer(Customer customer);

    Iterable<Customer> findAllTemplate();
}
