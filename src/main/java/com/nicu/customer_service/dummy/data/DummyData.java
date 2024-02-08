package com.nicu.customer_service.dummy.data;

import com.nicu.customer_service.model.Address;
import com.nicu.customer_service.model.Customer;
import com.nicu.customer_service.repositories.AddressRepository;
import com.nicu.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DummyData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    public DummyData(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address1 = new Address();
        address1.setStreetName("Street Alpha");
        address1.setStreetNo("12");
        address1.setCity("Berlin");
        address1.setCountry("DE");
        address1.setPostalCode("144786");


        Customer customer1 = new Customer();
        customer1.setFirstName("Eric");
        customer1.setLastName("Evans");
        customer1.setAge(Long.parseLong("19"));
        customer1.setEmail("asd@asd.com");
        customer1.setAddress(address1);

//#####################
        Address address2 = new Address();
        address2.setStreetName("Street Beta");
        address2.setStreetNo("33");
        address2.setCity("Bucharest");
        address2.setCountry("RO");
        address2.setPostalCode("333245");

        Customer customer2 = new Customer();
        customer2.setFirstName("Mark");
        customer2.setLastName("Twain");
        customer2.setAge(Long.parseLong("41"));
        customer2.setEmail("yyy@xoxw.com");
        customer2.setAddress(address2);

        Customer customerSaved1 = customerRepository.save(customer1);
        Customer customerSaved2 = customerRepository.save(customer2);

        Address address1Saved = addressRepository.save(address1);
        Address address2Saved = addressRepository.save(address2);

//######################


        System.out.println("Creating dummy data");
        System.out.println("Address Count: " + addressRepository.count());
        System.out.println("Customer Count: " + customerRepository.count());

    }
}










