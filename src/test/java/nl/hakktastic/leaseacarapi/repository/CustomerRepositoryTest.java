package nl.hakktastic.leaseacarapi.repository;

import nl.hakktastic.leaseacarapi.testdata.CustomerTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import static nl.hakktastic.leaseacarapi.testdata.CustomerTestData.*;

/** Sping Data JPA Test for {@link CustomerRepository}. */
@DataJpaTest
public class CustomerRepositoryTest {

  @Autowired CustomerRepository repository;

  @Test
  public void givenNoName_whenFindByName_thenExpectIllegalArgumentException() {

    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> repository.findByName(null))
        .withMessage("Parameter name in CustomerRepository.findByName must not be null");
  }

  @Test
  public void givenValidName_whenFindByName_thenReturnValidCustomer() {

    var customer = repository.findByName(CustomerTestData.NAME_VALID_HARRY_SNEL).get();

    Assertions.assertThat(customer).isNotNull();
    Assertions.assertThat(customer.getName()).isEqualTo(CustomerTestData.NAME_VALID_HARRY_SNEL);
  }

  @Test
  public void givenInvalidName_whenFindByName_thenReturnNull() {

    var customer = repository.findByName(CustomerTestData.NAME_INVALID_NON_EXISTING);

    Assertions.assertThat(customer.isEmpty());
  }

  @Test
  public void givenTooShortCustomerName_whenCreateCustomer_thenExpectConstraintViolation() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(() -> repository.save(CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_SHORT))
        .withMessageContaining("length must be between 2 and 50");
  }

  @Test
  public void givenTooCustomerLongName_whenCreateCustomer_thenExpectConstraintViolation() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(() -> repository.save(CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_LONG))
        .withMessageContaining("length must be between 2 and 50");
  }

  @Test
  public void givenNoPlaceName_whenCreateCustomer_thenExpectConstraintViolation() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(() -> repository.save(CUSTOMER_OBJECT_INVALID_NO_PLACE_NAME))
        .withMessageContaining("must not be null");
  }

  @Test
  public void givenTooShortPlaceName_whenCreateCustomer_thenExpectConstraintViolation() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(() -> repository.save(CUSTOMER_OBJECT_INVALID_PLACE_NAME_TOO_LONG))
        .withMessageContaining("length must be between 2 and 150");
  }

  @Test
  public void givenTooLongPlaceName_whenCreateCustomer_thenExpectConstraintViolation() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(() -> repository.save(CUSTOMER_OBJECT_INVALID_PLACE_NAME_TOO_LONG))
        .withMessageContaining("length must be between 2 and 150");
  }

  @Test
  public void givenInvalidEmail_whenCreateCustomer_thenExpectEmailConstraintViolation() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
        .isThrownBy(() -> repository.save(CUSTOMER_OBJECT_INVALID_EMAIL))
        .withMessageContaining("must be a well-formed email address");
  }
}
