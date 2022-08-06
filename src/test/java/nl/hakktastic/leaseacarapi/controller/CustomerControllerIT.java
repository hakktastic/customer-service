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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        .andExpect(jsonPath("$.name").value(CustomerTestData.NAME_VALID))
        .andExpect(jsonPath("$.street").value(STREET_NAME_VALID))
        .andExpect(jsonPath("$.houseNumber").value(HOUSE_NR_VALID))
        .andExpect(jsonPath("$.zipcode").value(ZIP_CODE_VALID))
        .andExpect(jsonPath("$.place").value(PLACE_NAME_VALID))
        .andExpect(jsonPath("$.email").value(EMAIL_VALID))
        .andExpect(jsonPath("$.phoneNumber").value(PHONE_NUMBER_VALID));
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

    var jsonStrCustomerId = new Gson().toJson(CUSTOMER_ID_VALID_2);

    mockMvc
        .perform(
            delete("/customers/{id}", CUSTOMER_ID_VALID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenInValidCustomerId_whenDeleteCustomer_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            delete("/customers/{id}", CUSTOMER_ID_INVALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenCustomerExist_whenGetAllCustomers_thenReturnOK() throws Exception {

    mockMvc.perform(get("/customers")).andExpect(status().isOk());
  }

  @Test
  public void givenValidCustomerId_whenGetCustomerById_thenReturnOK() throws Exception {

    mockMvc
        .perform(
            get("/customers/{id}", CUSTOMER_ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenInvalidCustomerId_whenGetCustomerById_thenReturnNoContent() throws Exception {

    mockMvc
        .perform(
            get("/customers/{id}", CUSTOMER_ID_INVALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  public void givenValidCustomerName_whenGetCustomerByName_thenReturnOK() throws Exception {

    mockMvc
        .perform(
            get("/customers/name/{name}", NAME_VALID_HARRY_SNEL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenInvalidCustomerName_whenGetCustomerByName_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            get("/customers/name/{name}", NAME_INVALID_NON_EXISTING)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}
