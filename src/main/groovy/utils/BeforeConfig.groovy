package utils

import com.github.javafaker.Faker
import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.Header
import org.junit.jupiter.api.BeforeAll

class BeforeConfig {

  private static final AuthenticationService authentication = new AuthenticationService()
  private static final UserCreator userCreator = new UserCreator()

  protected static String token
  static Header authorizationHeader

  private static def userPhoneNumber
  private static def userEmail

  static Faker faker

  @BeforeAll
  static void beforeAll() {
    RestAssured.baseURI = ConfigProperties.instance.baseUrl
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter())

    faker = new Faker()
    def fakerPhoneNumber = faker.phoneNumber().subscriberNumber(12)
    userPhoneNumber = "+${fakerPhoneNumber}"
    userEmail = faker.internet().emailAddress()

  }
}
