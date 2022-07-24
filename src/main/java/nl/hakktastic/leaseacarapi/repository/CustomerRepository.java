package nl.hakktastic.leaseacarapi.repository;

import nl.hakktastic.leaseacarapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** JPA Repository for Customer Entities. */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  /**
   * Custom interface method to find a {@link Customer} object based on the name of the user.
   *
   * @param name first and last name of the user
   * @return Returns a {@link Customer} entity
   */
  Optional<Customer> findByName(String name);
}
