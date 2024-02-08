package com.nicu.customer_service.services;

import com.nicu.customer_service.dto.CustomerDTO;
import com.nicu.customer_service.exception.CustomerNotFound;
import com.nicu.customer_service.model.Address;
import com.nicu.customer_service.model.Customer;
import com.nicu.customer_service.repositories.AddressRepository;
import com.nicu.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDTO findCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new CustomerNotFound(id);
        }

        return mapToDTO(customer.get());
    }

    @Override
    public Iterable<Customer> findAllTemplate() {
        return customerRepository.findAll();
    }


    @Override
    @Transactional
    public List<CustomerDTO> findCustomersByFirstNameOrLastName(String firstName, String lastName) {

        List<Customer> customers = customerRepository.findByFirstNameOrLastName(firstName, lastName);

        return customers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //age >= 18 AND  email or address must be completed
    @Override
    @Transactional
    public String createCustomer(CustomerDTO customerDTO) {
        Address address = customerDTO.getAddress();
        if (customerDTO.getAge() < 18) {
            return "The customer's age < 18 !";
        }

        if (address != null &&
                (customerDTO.getEmail() != null || address.hasAllFieldsCompleted())) {
            addressRepository.save(address);
            customerRepository.save(mapToCustomerEntity(customerDTO));
        } else {
            return "Email or address are required !";
        }

        return "Customer " + customerDTO.getLastName() + " successfully created !";
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

    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setAge(customer.getAge());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());

        return customerDTO;
    }

    private Customer mapToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAge(customerDTO.getAge());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());

        return customer;
    }
}

