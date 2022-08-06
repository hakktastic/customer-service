package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.entity.Customer;
import nl.hakktastic.leaseacarapi.repository.CustomerRepository;
import nl.hakktastic.leaseacarapi.testdata.CustomerTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock CustomerRepository customerRepository;
  @InjectMocks CustomerService customerService;

  @Test
  public void givenValidArgs_whenCreateCustomer_thenReturnValidCustomerObject() {

    when(customerRepository.save(any(Customer.class)))
        .thenReturn(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);

    var customer = customerService.createCustomer(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);

    assertThat(customer.get())
        .isNotNull()
        .isEqualTo(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);
  }

  @Test
  public void givenValidCustomerId_whenDeleteCustomer_thanDeleteCustomer() {

    when(customerService.deleteCustomer(any(Integer.class))).thenReturn(true);

    var isCustomerDeleted = customerService.deleteCustomer(CustomerTestData.CUSTOMER_ID_VALID);

    assertThat(isCustomerDeleted).isEqualTo(true);
  }

  @Test
  public void givenInvalidCustomerId_whenDeleteCustomer_thanDeleteCustomer() {

    when(customerService.deleteCustomer(any(Integer.class))).thenReturn(false);

    var isCustomerDeleted = customerService.deleteCustomer(CustomerTestData.CUSTOMER_ID_VALID);

    assertThat(isCustomerDeleted).isEqualTo(false);
  }

  @Test
  public void givenThreeCustomersInRepository_whenGetAllCustomers_thenReturnThreeCustomers() {

    when(customerRepository.findAll())
        .thenReturn(
            List.of(
                CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS,
                CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS,
                CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS));

    var customerList = customerRepository.findAll();

    assertThat(customerList).isNotEmpty().hasSize(3);
  }

  @Test
  public void givenValidCustomerId_whengetSingleCustomer_thenReturnCustomerObject() {

    when(customerRepository.findById(any(Integer.class)))
        .thenReturn(Optional.ofNullable(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS));

    var customer = customerService.getSingleCustomer(CustomerTestData.CUSTOMER_ID_VALID);

    assertThat(customer.get())
        .isNotNull()
        .isEqualTo(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);
  }

  @Test
  public void givenValidCustomerName_whengetSingleCustomer_thenReturnCustomerObject() {

    when(customerRepository.findByName(any(String.class)))
        .thenReturn(Optional.ofNullable(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS));

    var customer = customerService.getSingleCustomer(CustomerTestData.NAME_VALID);

    assertThat(customer.get())
        .isNotNull()
        .isEqualTo(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);
    assertThat(customer.get().getName()).isEqualTo(CustomerTestData.NAME_VALID);
  }
}
