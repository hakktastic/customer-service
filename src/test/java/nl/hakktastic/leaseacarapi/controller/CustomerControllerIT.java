package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.repository.CustomerRepository;
import nl.hakktastic.leaseacarapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerControllerIT {

  @Autowired CustomerRepository customerRepository;
  @Autowired CustomerService customerService;
  @Autowired CustomerController customerController;
}
