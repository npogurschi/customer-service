package com.nicu.customer_service.controllers;

import com.nicu.customer_service.model.Customer;
import com.nicu.customer_service.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {

        return customerService.findCustomer(id);
    }

    /**
     * Returns the Customer(s) by first name or last name  with NO  key sensitive.
     * @param firstName
     * @param lastName
     * @return
     */
    @GetMapping("/customer")
    public List<Customer> getCustomerByName(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName) {
        return customerService.findCustomersByFirstNameOrLastName(firstName, lastName);
    }

    @PostMapping("/customer")
    public String createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customer")
    public String updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

}