package nl.hakktastic.leaseacarapi.repository;

import nl.hakktastic.leaseacarapi.testdata.CustomerTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/** Sping Data JPA Test for {@link CustomerRepository}. */
@DataJpaTest
public class CustomerRepositoryTest {

  @Autowired CustomerRepository repository;

  @Test
  public void givenNoName_whenCreatingCustomer_thenExpectIllegalArgumentException() {

    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> this.repository.findByName(null))
        .withMessage("Parameter name in CustomerRepository.findByName must not be null");
  }

  @Test
  public void givenValidName_whenCreatingCustomer_thenExpectValidCustomer() {

    var customer = this.repository.findByName(CustomerTestData.NAME_VALID_HARRY_SNEL).get();

    Assertions.assertThat(customer).isNotNull();
    Assertions.assertThat(customer.getName()).isEqualTo(CustomerTestData.NAME_VALID_HARRY_SNEL);
  }

  @Test
  public void givenInvalidName_whenCreatingCustomer_thenExpectNull() {

    var customer = this.repository.findByName(CustomerTestData.NAME_INVALID_NON_EXISTING);

    Assertions.assertThat(customer.isEmpty());
  }
}
