package nl.hakktastic.leaseacarapi.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static nl.hakktastic.leaseacarapi.testdata.CustomerTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Entity Test for the class {@link Customer}.
 *
 * <p>This test is not necessarily needed because it will be covered in the {@link
 * nl.hakktastic.leaseacarapi.repository.CustomerRepository} tests. However, it's nice to see how
 * hibernate validations are being done.
 */
public class CustomerTest {

  @Autowired private static ValidatorFactory factory;

  @Autowired private static Validator validator;

  @BeforeAll
  public static void beforeAll() {

    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterAll
  public static void afterAll() {
    factory.close();
  }

  @Test
  public void givenNoArgs_whenCreateCustomer_thenExpectConstraintViolations() {

    var validations = validator.validate(CUSTOMER_OBJECT_INVALID_NO_ARGS);
    assertThat(validations).isNotEmpty().hasSize(6);
  }

  @Test
  public void givenAllArgs_whenCreateCustomer_thenExpectNoConstraintViolations() {

    var validations = validator.validate(CUSTOMER_OBJECT_VALID_ALL_ARGS);
    assertThat(validations).isEmpty();
  }

  @Test
  public void givenNoCustomerName_whenCreateCustomer_thenExpectConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_NO_CUSTOMER_NAME);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage()).isEqualTo("must not be null");
  }

  @Test
  public void givenTooShortCustomerName_whenCreateCustomer_thenExpectConstraintViolation() {

    var validations = this.validator.validate(CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_SHORT);

    assertThat(validations).isNotEmpty().hasSize(1);
    assertThat(validations.iterator().next().getMessage())
        .isEqualTo("length must be between 2 and 50");
  }

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
}
