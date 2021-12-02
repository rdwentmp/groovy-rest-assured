package utils

import groovy.json.JsonOutput

import static io.restassured.RestAssured.given
import static io.restassured.http.ContentType.JSON

class AuthenticationService {

    /**
     * Provide Bearer token to authorize request.
     *
     * @return Authorization Bearer token <br><br>
     * Example:
     * <p>
     *     Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrOTY2MTIzMTQxNDEyIiwiaWF0IjoxNjMwNDM5MDM0LCJleHAiOjE2MzA0Mzk2MzQsInRva</br>
     *     2VuVHlwZSI6IkxBTkRMT1JEIiwidXVpZCI6Ijc2NDFiNGE2LTMzYjgtNGU5My05OTNiLWM4MmU4OThjMGFjYiIsIm9yZ2FuaXphdGlvbnMiOlt7InV1aW</br>
     *     QiOiIwZmEyNDg5OS01YWYyLTQ0YTgtODk1Zi01MWYyZDYzOWZmZjgiLCJ0eXBlIjoiQ09NUEFOWSIsInJvbGUiOiJBRE1JTiIsIm5hbWUiOn</br>
     *     sibGFiZWxzIjp7ImFyIjoiVGVzdCBDb21wYW55MSIsImVuIjoiVGVzdCBDb21wYW55MSJ9fX0seyJ1dWlkIjoiYzBkYTM0ODktNjAxNS00ZGV</br>
     *     mLWFkOWItYTEyYjY4Nzk5MDNkIiwidHlwZSI6IkNPTVBBTlkiLCJyb2xlIjoiQURNSU4iLCJuYW1lIjp7ImxhYmVscyI6eyJhciI6IlRlc3Qg</br>
     *     Q29tcGFueTEiLCJlbiI6IlRlc3QgQ29tcGFueTEifX19LHsidXVpZCI6IjM5NGQ2NTZhLTgyZGItNDVlYy04NzZjLTNjNTc0YzE3OGFkMiIv</br>
     *     sInR5cGUiOiJDT01QQU5ZIiwicm9sZSI6IkFETUlOIiwibmFtZSI6eyJsYWJlbHMiOnsiYXIiOiJUZXN0IENvbXBhbnkxIiwiZW4iOiJUZXN0</br>
     *     IENvbXBhbnkxIn19fSx7InV1aWQiOiJkM2MzNTNmZS1kYmZjLTRiNDMtYThjYy02NWI5ZDYzN2NiMzIiLCJ0eXBlIjoiSU5ESVZJRFVBTCIs</br>
     *     nJvbGUiOiJBRE1JTiIsIm5hbWUiOnsibGFiZWxzIjp7ImFyIjoic3RyaW5nIiwiZW4iOiJzdHJpbmcifX19XX0.</br>
     *     Lx9Hhhyh7clUFAfCjGbjKgZM68iD4SdmnYzlsVAIiVf9TOGZWoMu4gEspI4LCJXU9OV4Yo8MQiAoVrpoKfYVpA</br>
     * </p>
     */

    public static final String DEFAULT_PHONE_NUMBER = '+966123141412'

  static def authorize(userPhoneNumber) {
    def phoneNumber = userPhoneNumber
    def otpJson = JsonOutput.toJson(
          [
            phoneNumber: "${phoneNumber}"
          ])

        def otp = given()
                .contentType(JSON)
                .body(otpJson)
        .expect()
                .statusCode(201)
        .when()
                .post('/otp').path('otp')


        def otpValidationJson = JsonOutput.toJson(
                [
                        phoneNumber: "${phoneNumber}",
                        otp: "${otp}"
                ]
        )
        def token = given()
                .contentType(JSON)
                .body(otpValidationJson)
                .post('/otp/validation')
                .path('token')

        def bearerToken = "Bearer ${token}"
        return bearerToken
    }

}
