package com.nicu.customer_service.controllers;

import com.nicu.customer_service.domain.Customer;
import com.nicu.customer_service.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.findAll();
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {

        return customerService.findCustomer(id);
    }

    @GetMapping("/getCustomerByName")
    public Customer getCustomerByName(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName) {
        return customerService.findCustomerByFirstNameOrLastName(firstName, lastName);
    }

    @PostMapping("/createCustomer")
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

}