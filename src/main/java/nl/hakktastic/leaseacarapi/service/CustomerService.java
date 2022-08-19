package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.entity.Customer;
import nl.hakktastic.leaseacarapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Service object for managing {@link nl.hakktastic.leaseacarapi.entity.Customer} entities. */
@Service
public class CustomerService {

  @Autowired private CustomerRepository repository;

  /**
   * Create a {@link Customer} entity.
   *
   * @param customer {@link Customer}
   * @return Returns the customer created as an {@link Optional}
   */
  public Optional<Customer> createCustomer(Customer customer) {

    return Optional.of(this.repository.save(customer));
  }

  /**
   * Delete a {@link Customer} entity.
   *
   * @param id ID of the customer to be deleted
   * @return Returns TRUE if customer is found, otherwise returns FALSE
   */
  public boolean deleteCustomer(int id) {

    if (repository.existsById(id)) {

      repository.deleteById(id);
      return true;
    }

    return false;
  }

  /**
   * Get all {@link Customer} entities from the repository.
   *
   * @return Returns a {@link List} with found {@link Customer} entities
   */
  public List<Customer> getAllCustomers() {

    return this.repository.findAll();
  }

  /**
   * Find a single {@link Customer} entity with provided ID.
   *
   * @param id ID of the car to be returned
   * @return Returns an {@link Optional} containing the {@link Customer} entity
   */
  public Optional<Customer> getSingleCustomer(int id) {

    return this.repository.findById(id);
  }

  /**
   * Find a single {@link Customer} entity with provided name.
   *
   * @param name of the customer to be returned
   * @return Returns an {@link Optional} containing the {@link Customer} entity
   */
  public Optional<Customer> getSingleCustomer(String name) {

    return this.repository.findByName(name);
  }
}
