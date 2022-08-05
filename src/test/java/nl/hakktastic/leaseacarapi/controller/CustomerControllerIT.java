package nl.hakktastic.leaseacarapi.controller;

import com.google.gson.Gson;
import nl.hakktastic.leaseacarapi.testdata.CustomerTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static nl.hakktastic.leaseacarapi.testdata.CustomerTestData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Integration Test for {@link CustomerController} */
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  public void givenValidArgs_whenPostCustomer_thenReturnIsCreated() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_VALID_ALL_ARGS);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(CustomerTestData.NAME_VALID)))
        .andExpect(jsonPath("$.street", is(STREET_NAME_VALID)))
        .andExpect(jsonPath("$.houseNumber", is(HOUSE_NR_VALID)))
        .andExpect(jsonPath("$.zipcode", is(ZIP_CODE_VALID)))
        .andExpect(jsonPath("$.place", is(PLACE_NAME_VALID)))
        .andExpect(jsonPath("$.email", is(EMAIL_VALID)))
        .andExpect(jsonPath("$.phoneNumber", is(PHONE_NUMBER_VALID)));
  }

  @Test
  public void givenNoArgs_whenPostCustomer_thenReturnBadRequest() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_INVALID_NO_ARGS);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenInValidCustomerName_whenPostCustomer_thenReturnBadRequest() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_INVALID_NO_CUSTOMER_NAME);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenInvalidStreetName_whenPostCustomer_thenReturnBadRequest() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_INVALID_STREET_NAME);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenInvalidZipcode_whenPostCustomer_thenReturnBadRequest() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_INVALID_ZIP_CODE);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenInvalidPlaceName_whenPostCustomer_thenReturnBadRequest() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_INVALID_NO_PLACE_NAME);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenInvalidEmail_whenPostCustomer_thenReturnBadRequest() throws Exception {

    var jsonStrCustomer = new Gson().toJson(CUSTOMER_OBJECT_INVALID_EMAIL);

    mockMvc
        .perform(
            post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonStrCustomer))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenValidCustomerId_whenDeleteCustomer_thenReturnOK() throws Exception {

    var jsonStrCustomerId = new Gson().toJson(CUSTOMER_ID_VALID);

    mockMvc
        .perform(
            delete("/customers/{id}", CUSTOMER_ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenInValidCustomerId_whenDeleteCustomer_thenReturnOK() throws Exception {

    // TODO actual is 200, expected is 400
    mockMvc
        .perform(
            delete("/customers/{id}", CUSTOMER_ID_INVALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
