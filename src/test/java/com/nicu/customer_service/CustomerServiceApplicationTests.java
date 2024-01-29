package com.nicu.customer_service;

import com.nicu.customer_service.domain.Address;
import com.nicu.customer_service.domain.Customer;
import com.nicu.customer_service.repositories.CustomerRepository;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private MockMvc mvc;


    @Test
    void contextLoads() {
    }

    @Test
    void testGetCustomers() throws Exception {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();

        assertEquals(2, customerList.size());
    }

    @Test
    void testGetCustomerByFirstName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getCustomerByName")
                        .param("lastName", "Evans"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("asd@asd.com"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getCustomerById/1")
                        .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("asd@asd.com"));
    }

}
