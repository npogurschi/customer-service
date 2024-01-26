package com.nicu.customer_service.services;

import com.nicu.customer_service.domain.Address;
import com.nicu.customer_service.domain.Customer;
import com.nicu.customer_service.repositories.AddressRepository;
import com.nicu.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
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
    public Customer findCustomerByFirstNameOrLastName(String firstName, String lastName) {
        if ((firstName != null && !StringUtils.isEmpty(firstName) )||
                (lastName != null && !StringUtils.isEmpty(lastName))) {
            return customerRepository.findCustomerByFirstNameOrLastName(firstName, lastName);

        }
        return new Customer();
    }

    //age >= 18 AND  email or address must be completed
    @Override
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

