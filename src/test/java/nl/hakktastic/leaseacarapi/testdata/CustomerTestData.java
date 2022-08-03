package nl.hakktastic.leaseacarapi.testdata;

import nl.hakktastic.leaseacarapi.entity.Customer;

public final class CustomerTestData {

  /** Customer Numbers. */
  public static final int VALID_CUSTOMER_ID = 1000;

  /** Customer Names. */
  public static final String NAME_VALID = "Customer Name";

  public static final String NAME_VALID_HARRY_SNEL = "Harry Snel";
  public static final String NAME_INVALID_NON_EXISTING = "Non Existing Customer";
  public static final String NAME_INVALID_MAX_LENGTH =
      "This is an invalid customer name with more than fifty characters";
  public static final String NAME_INVALID_MIN_LENGTH = "A";

  /** Street Names. */
  public static final String STREET_NAME_VALID = "Musuemplein";

  public static final String STREET_NAME_INVALID_MAX_LENGTH =
      "This is an invalid street  name with more than twohunderd and fifty  character. This is an invalid street  name with more than twohunderd and fifty  characters. This is an invalid street  name with more than twohunderd and fifty  characters. This is an invalid street  name with more than twohunderd and fifty  characters.";
  public static final String STREET_NAME_INVALID_MIN_LENGTH = "AB";

  /** house numbers. */
  public static final int HOUSE_NR_VALID = 25;

  /** ZIP Codes. */
  public static final String ZIP_CODE_VALID = "1001AS";

  public static final String ZIP_CODE_INVALID_MAX_LENGTH = "123456789101112131415";
  public static final String ZIP_CODE_INVALID_MIN_LENGTH = "10";

  /** Place Names. */
  public static final String PLACE_NAME_VALID = "AMSTERDAM";

  public static final String PLACE_NAME_INVALID_MIN_LENGTH = "A";
  public static final String PLACE_NAME_INVALID_MAX_LENGTH =
      "This is an invalid street  name with more than one hunderd and fifty  characters.This is an invalid street  name with more than one hunderd and fifty  characters.";

  /** Email addresses. */
  public static final String EMAIL_VALID = "valid@gmail.com";

  public static final String EMAIL_INVALID = "invalidemailatgmail.com";

  /** Phone Numbers. */
  public static Integer PHONE_NUMBER_VALID = 1026142315;

  /** Customer objects. */
  public static final Customer CUSTOMER_OBJECT_INVALID_NO_ARGS = new Customer().builder().build();

  public static final Customer CUSTOMER_OBJECT_VALID_ALL_ARGS =
      new Customer()
          .builder()
          .name(NAME_VALID)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_VALID)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_EMAIL =
      new Customer()
          .builder()
          .name(NAME_VALID)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_VALID)
          .email(EMAIL_INVALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_NO_CUSTOMER_NAME =
      new Customer()
          .builder()
          // .name(null)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_VALID)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_SHORT =
      new Customer()
          .builder()
          .name(NAME_INVALID_MIN_LENGTH)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_VALID)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_CUSTOMER_NAME_TOO_LONG =
      new Customer()
          .builder()
          .name(NAME_INVALID_MAX_LENGTH)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_VALID)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_NO_PLACE_NAME =
      new Customer()
          .builder()
          .name(NAME_VALID)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          // .place(PLACE_NAME_VALID)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_PLACE_NAME_TOO_SHORT =
      new Customer()
          .builder()
          .name(NAME_VALID)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_INVALID_MIN_LENGTH)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();

  public static final Customer CUSTOMER_OBJECT_INVALID_PLACE_NAME_TOO_LONG =
      new Customer()
          .builder()
          .name(NAME_VALID)
          .street(STREET_NAME_VALID)
          .houseNumber(HOUSE_NR_VALID)
          .zipcode(ZIP_CODE_VALID)
          .place(PLACE_NAME_INVALID_MAX_LENGTH)
          .email(EMAIL_VALID)
          .phoneNumber(PHONE_NUMBER_VALID)
          .build();
}
