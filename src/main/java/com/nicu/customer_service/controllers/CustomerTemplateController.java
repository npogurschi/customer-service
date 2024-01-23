package com.nicu.customer_service.controllers;

import com.nicu.customer_service.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerTemplateController {

    private final CustomerService customerService;

    public CustomerTemplateController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customersTemplate")
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerService.findAllTemplate());

        return "customers";
    }

}