package com.nicu.customer_service.dto;


import com.nicu.customer_service.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long age;
    private String email;
    private Address address;
}