package com.nicu.customer_service.services;

import com.nicu.customer_service.model.Address;
import com.nicu.customer_service.model.Customer;
import com.nicu.customer_service.repositories.AddressRepository;
import com.nicu.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer findCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found !");
        }

        return customer.get();
    }

    @Override
    public Iterable<Customer> findAllTemplate() {
        return customerRepository.findAll();
    }


    @Override
    @Transactional
    public List<Customer> findCustomersByFirstNameOrLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameOrLastName(firstName, lastName);

    }

    //age >= 18 AND  email or address must be completed
    @Override
    @Transactional
    public String createCustomer(Customer customer) {
        Address address = customer.getAddress();
        if (customer.getAge() < 18) {
            return "The customer's age < 18 !";
        }

        if (address != null &&
                (customer.getEmail() != null || address.hasAllFieldsCompleted())) {
            addressRepository.save(address);
            customerRepository.save(customer);
        } else {
            return "Email or address are required !";
        }
        
        return "Customer " + customer.getLastName() + " successfully created !";
    }

    @Override
    @Transactional
    public String updateCustomer(Customer customer) {
        Long customerId = customer.getId();
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);

        if (existingCustomer.isEmpty()) {
            return "Customer not found !";
        }
        addressRepository.save(customer.getAddress());

        return "Customer " + existingCustomer.get().getLastName() + " updated";
    }
}

