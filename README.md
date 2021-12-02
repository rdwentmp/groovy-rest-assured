# REST-Assured Groovy template

### API E2E Tests

## Stack

**Base**

- [Gradle](https://docs.gradle.org/current/userguide/userguide.html) 7.0
- [Groovy](http://docs.groovy-lang.org/docs/latest/html/documentation/) 3.0.8
- [RestAssured](https://rest-assured.io/) 4.4.0


Run all project tests with ```gradle test -Pe2eTests```


Example test:
``` groovy
  @Test
  void 'Should get organization and return Status 200'() {
    setup: //only for increasing readability
        def organizationUUID = createOrganizationRequestCall()

    test: //only for increasing readability
        given().
            header(authorizationHeader).
            contentType(JSON).

        expect().
            statusCode(200).

        when().
            get("/organization/{organizationUUID}", organizationUUID)
  }
```



