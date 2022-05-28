package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.entity.Customer;
import nl.hakktastic.leaseacarapi.repository.CustomerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for Customer Service.
 *
 * @author HAKKI CABUK
 */
@RestController
public class CustomerController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepository repository;

    /**
     * Create Customer.
     *
     * @param customer {@link Customer} data
     * @return Returns a {@link Customer} Entity
     */
    @PostMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        final Customer customerEntity = repository.save(customer);
        return new ResponseEntity<>(customerEntity, HttpStatus.CREATED);
    }

    /**
     * Delete Customer Entity.
     *
     * @param id ID of Customer Entity
     * @return Returns HTTP Response Code 202 Accepted if Customer is deleted
     */
    @DeleteMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {

        final Customer customerEntity = repository.getOne(id);
        repository.delete(customerEntity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Get all Customer Entities.
     *
     * @return Returns a {@link List} with all Customer Entities
     */
    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {

        final List<Customer> customerEntityList = repository.findAll();

        if (!customerEntityList.isEmpty()) {

            return new ResponseEntity<>(customerEntityList, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get Customer Entity by ID.
     *
     * @param id Object ID of Customer Entity
     * @return Returns a {@link Customer} Entity
     */
    @GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {

        final ResponseEntity<Customer> responseEntity;
        final Optional<Customer> optionalCustomerEntity = repository.findById(id);

        if (optionalCustomerEntity.isPresent()) {

            responseEntity = new ResponseEntity<>(optionalCustomerEntity.get(), HttpStatus.OK);

            logger.info("Get Customer by ID --> Response Code -> {} - Response -> {} ",
                    responseEntity.getStatusCodeValue(), responseEntity.getBody());

        } else {

            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    /**
     * Get Customer Entity by name.
     *
     * @param name First- and last name of Customer Entity
     * @return Returns a {@link Customer} Entity
     */
    @GetMapping(path = "/customers/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {


        final Optional<Customer> optionalCustomerEntity = repository.findByName(name);

        if (optionalCustomerEntity.isPresent()) {

            return new ResponseEntity<>(optionalCustomerEntity.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
