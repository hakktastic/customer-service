package nl.hakktastic.leaseacarapi.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.entity.Customer;
import nl.hakktastic.leaseacarapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/** Rest controller for Customer Service. */
@RestController
@Slf4j
public class CustomerController {

  @Autowired private CustomerService customerService;

  /**
   * Create Customer Entity.
   *
   * @param customer {@link Customer} data
   * @return Returns a {@link Customer} Entity
   */
  @PostMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {

    log.info("create customer --> starting creation of customer -> {}", customer);

    var optionalCustomer = this.customerService.createCustomer(customer);
    var status = (optionalCustomer.isPresent()) ? HttpStatus.CREATED : HttpStatus.NOT_FOUND;

    log.info(
        "create customer --> response code -> {} ({}) - response body -> {} ",
        status.value(),
        status.name(),
        optionalCustomer.orElseGet(() -> null));

    return new ResponseEntity<>(optionalCustomer.orElseGet(() -> null), status);
  }

  /**
   * Delete Customer Entity.
   *
   * @param id ID of Customer Entity
   * @return Returns HTTP Response Code 202 Accepted if Customer is deleted
   */
  @DeleteMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteCustomer(@PathVariable @Valid int id) {

    log.info("delete customer --> starting deletion of customer with id-> {}", id);

    var isCustomerDeleted = this.customerService.deleteCustomer(id);

    if (isCustomerDeleted) {

      log.info(
          "delete customer --> response code -> {} ({})",
          HttpStatus.OK.value(),
          HttpStatus.OK.name());

      return new ResponseEntity<>("Customer deleted successsfully", HttpStatus.OK);

    } else {

      log.info(
          "delete customer --> response code -> {} ({})",
          HttpStatus.NOT_FOUND.value(),
          HttpStatus.NOT_FOUND.name());

      return new ResponseEntity<>(
          "Unable to delete Customer because unable to find Customer with provided ID",
          HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Get all Customer Entities.
   *
   * @return Returns a {@link List} with all Customer Entities
   */
  @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Customer>> getAllCustomers() {

    log.info("get customers --> starting retrieval of all customers");

    var customerEntityList = this.customerService.getAllCustomers();
    HttpStatus status = (!customerEntityList.isEmpty()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

    log.info(
        "get customers --> response code -> {} ({}) - nr of found customers -> {}",
        status.value(),
        status.name(),
        (!(customerEntityList.isEmpty()) ? customerEntityList.size() : "-"));

    return new ResponseEntity<>(customerEntityList, status);
  }

  /**
   * Get Customer Entity by ID.
   *
   * @param id Object ID of Customer Entity
   * @return Returns a {@link Customer} Entity
   */
  @GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomerById(@PathVariable @Valid int id) {

    log.info("get customer --> starting retrieval of car with id -> {}", id);

    var optionalCustomer = this.customerService.getSingleCustomer(id);
    var status = (optionalCustomer.isPresent()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

    log.info(
        "get customer --> response code -> {} ({}) - response body -> {} ",
        status.value(),
        status.name(),
        optionalCustomer.orElseGet(() -> null));

    return new ResponseEntity<>(optionalCustomer.orElseGet(() -> null), status);
  }

  /**
   * Get Customer Entity by name.
   *
   * @param name First- and last name of Customer Entity
   * @return Returns a {@link Customer} Entity
   */
  @GetMapping(path = "/customers/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomerByName(@PathVariable @Valid String name) {

    log.info("get customer --> starting retrieval of car with id -> {}", name);

    var optionalCustomer = this.customerService.getSingleCustomer(name);
    var status = (optionalCustomer.isPresent()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

    log.info(
        "get customer --> response code -> {} ({}) - response body -> {} ",
        status.value(),
        status.name(),
        optionalCustomer.orElseGet(() -> null));

    return new ResponseEntity<>(optionalCustomer.orElseGet(() -> null), status);
  }
}
