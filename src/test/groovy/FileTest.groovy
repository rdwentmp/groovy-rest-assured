import utils.BeforeConfig
import com.google.common.net.MediaType
import org.junit.jupiter.api.*

import static io.restassured.RestAssured.*
import static io.restassured.http.ContentType.MULTIPART


@DisplayName("File module tests")
class FileTest extends BeforeConfig{

  // Example multipart upload test
  @Test
  void 'Should upload attachment JPEG and return Status 200'() {

    File file = new File("./src/test/resources/user/image.jpeg")
      given().
              multiPart("file", file, MediaType.JPEG.toString()).
              contentType(MULTIPART).
              header(authorizationHeader).
      expect().
              statusCode(200).
      when().
              post('/files/attachments')
  }
}
