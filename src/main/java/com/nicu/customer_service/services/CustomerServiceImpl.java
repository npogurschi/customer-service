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

    @Override
    public String createCustomer(Customer customer) {
        Address address = customer.getAddress();
        if (customer.getAge() < 18) {
            return "The customer's age < 18, cannot create it !";
        }

        if (address != null) {
            addressRepository.save(address);
            customerRepository.save(customer);

        }
        return "Customer " + customer.getLastName() + " successfully created ~";
    }
}

