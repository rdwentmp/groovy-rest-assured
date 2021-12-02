import utils.BeforeConfig
import utils.UserCreator

import org.junit.jupiter.api.*
import static io.restassured.RestAssured.*
import static io.restassured.http.ContentType.JSON


@DisplayName("User module tests")
class UserTest extends BeforeConfig{


	@Test
	void 'Should create user and return status 201'() {
		given().
				body(UserCreator.createUserJSON()).
				contentType(JSON).
				header(authorizationHeader).
				expect().
				statusCode(201).
				when().
				post('/user')
	}
}