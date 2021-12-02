package utils

import com.github.javafaker.Faker

import java.time.LocalDate
import java.time.ZoneId
import java.util.concurrent.TimeUnit

class UserCreator {

	def static createUserJSON() {
		def faker = new Faker()
		def userEmail = faker.internet().emailAddress()
		def address = faker.address();

		return """
        {
            "personalInfo": {
                        "company": "${faker.company().buzzword()}",
                        "dateOfBirth": "${LocalDate.ofInstant(faker.date().past(180, TimeUnit.DAYS).toInstant(), ZoneId.of("UTC"))}",
                        "email": "${userEmail}",
                        "fullEnglishName": "${faker.name().fullName()}",
                        "gender": "FEMALE",
                        "jobTitle": "${faker.job().title()}",
                        "nationalId": "1234567890",
                        "idExpirationDate": "${LocalDate.ofInstant(faker.date().future(180, TimeUnit.DAYS).toInstant(), ZoneId.of("UTC"))}",
                        "nationality": "${faker.country().countryCode2().toUpperCase()}",
                        "vatNumber": "${faker.number().randomNumber(15, true)}"
                      }
        }
    """
	}
}