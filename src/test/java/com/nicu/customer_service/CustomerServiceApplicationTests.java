package com.nicu.customer_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicu.customer_service.model.Address;
import com.nicu.customer_service.model.Customer;
import com.nicu.customer_service.repositories.AddressRepository;
import com.nicu.customer_service.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerServiceApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    @Autowired
    private EntityManager entityManager;


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
        mvc.perform(MockMvcRequestBuilders.get("/api/customer")
                        .param("lastName", "Evans"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("asd@asd.com"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/customer/1")
                        .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value("19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("asd@asd.com"));
    }

    //@Test
    void testCreateCustomer() throws Exception {
        Address address = new Address();
        address.setCity("Montreal");
        address.setCountry("Canada");
        address.setPostalCode("123456");
        address.setId(Long.parseLong("10"));
        address.setStreet("Street 14");

        address = entityManager.merge(address);

        Customer customer = new Customer();
        customer.setFirstName("Michel");
        customer.setLastName("Lala");
        customer.setAge(Long.parseLong("33"));
        customer.setEmail("XXX@canada.com");
        customer.setAddress(address);

        // Perform a POST request with the customer object as content
        mvc.perform(MockMvcRequestBuilders.post("/api/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Customer Me successfully created !"));

        // Verify that the customer is added to the repository
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        assertEquals(1, customerList.size());
        Customer savedCustomer = customerList.get(0);
        assertEquals("Michel", savedCustomer.getFirstName());
        assertEquals("Lala", savedCustomer.getLastName());
    }


}
