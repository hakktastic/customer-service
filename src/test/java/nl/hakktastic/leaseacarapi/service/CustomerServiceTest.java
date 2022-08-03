package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.entity.Customer;
import nl.hakktastic.leaseacarapi.repository.CustomerRepository;
import nl.hakktastic.leaseacarapi.testdata.CustomerTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock CustomerRepository customerRepository;
  @InjectMocks CustomerService customerService;

  // create
  @Test
  public void givenValidCustomerObject_whenCreatingCustomer_thenExpectCustomerToBeCreated() {

    Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
        .thenReturn(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);

    var customer = customerService.createCustomer(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);

    Assertions.assertThat(customer).isNotEmpty();
    Assertions.assertThat(customer.get())
        .isNotNull()
        .isEqualTo(CustomerTestData.CUSTOMER_OBJECT_VALID_ALL_ARGS);
  }

  @Test
  public void givenNoCustomerName_whenCreateCustomer_thenExpectConstraintViolationException() {

    Mockito.when(customerRepository.save(CustomerTestData.CUSTOMER_OBJECT_INVALID_NO_CUSTOMER_NAME))
        .thenThrow(ConstraintViolationException.class);

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(
            () ->
                customerService.createCustomer(
                    CustomerTestData.CUSTOMER_OBJECT_INVALID_NO_CUSTOMER_NAME));
  }
  /*
  @Test
  public void givenTooShortCustomerName_whenCreateCustomer_thenExpectIllegalArgumentException() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_SHORT);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage())
        .isEqualTo("length must be between 2 and 50");
  }

  /*
  @Test
  public void givenTooCustomerLongName_whenCreateCustomer_thenExpectConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_LONG);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage())
        .isEqualTo("length must be between 2 and 50");
  }

  @Test
  public void givenNoPlaceName_whenCreateCustomer_thenExpectConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_NO_PLACE_NAME);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage()).isEqualTo("must not be null");
  }

  @Test
  public void givenTooShortPlaceName_whenCreateCustomer_thenExpectConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_PLACE_NAME_TOO_LONG);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage())
        .isEqualTo("length must be between 2 and 150");
  }

  @Test
  public void givenTooLongPlaceName_whenCreateCustomer_thenExpectConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_PLACE_NAME_TOO_LONG);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage())
        .isEqualTo("length must be between 2 and 150");
  }

  @Test
  public void givenInvalidEmail_whenCreateCustomer_thenExpectEmailConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_EMAIL);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage())
        .isEqualTo("must be a well-formed email address");
  }

  // get single customer

  // get all customers

  // delete customer

   */

}
