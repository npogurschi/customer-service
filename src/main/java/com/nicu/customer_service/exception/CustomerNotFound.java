package com.nicu.customer_service.exception;

public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound(Long id) {
        super("Customer Id: " + id + " not found !");
    }
}
