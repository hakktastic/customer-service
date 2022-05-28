package nl.hakktastic.leaseacarapi.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * JPA Customer entity.
 */
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String street;
    private int houseNumber;
    private String zipcode;
    private String place;
    @Pattern(regexp = "\"^(.+)@(.+)$\"")
    @NotNull
    private String email;
    private int phoneNumber;


    /**
     * Default Constructor.
     */
    public Customer() {
    }

    /**
     * Constructor.
     *
     * @param name        first and last name of customer
     * @param street      the street of the customer
     * @param houseNumber the house number of the customer
     * @param zipcode     the zip code of the customer
     * @param place       the place of residence of the ucustomerser
     * @param email       the email address of the customer
     * @param phoneNumber the phone number of the customer
     */
    public Customer(String name, String street, int houseNumber, String zipcode, String place,
                    String email, int phoneNumber) {

        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.place = place;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {

        boolean equation = false;

        if (obj instanceof Customer) {

            final Customer otherEntity = (Customer) obj;

            equation = new EqualsBuilder().appendSuper(super.equals(obj))
                    .append(this.getId(), otherEntity.getId()).isEquals();
        }

        return equation;
    }
}
