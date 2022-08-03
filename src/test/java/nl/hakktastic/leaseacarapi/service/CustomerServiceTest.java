package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.repository.CustomerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock CustomerRepository customerRepository;
  @InjectMocks CustomerService customerService;
}
