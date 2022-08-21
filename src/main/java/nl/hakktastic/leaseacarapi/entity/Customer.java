package nl.hakktastic.leaseacarapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/** JPA Customer entity. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Length(min = 2, max = 50)
  private String name;

  @NotNull
  @Length(min = 3, max = 250)
  private String street;

  @NotNull
  @Range(min = 1)
  private int houseNumber;

  @NotNull
  @Length(min = 4, max = 10)
  private String zipcode;

  @NotNull
  @Length(min = 2, max = 150)
  private String place;

  @NotNull @Email private String email;

  @NotNull private int phoneNumber;
}
