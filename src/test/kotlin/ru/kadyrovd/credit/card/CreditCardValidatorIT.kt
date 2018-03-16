package ru.kadyrovd.credit.card

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreditCardValidatorIT {

	@LocalServerPort
	private val port: Int = 0

	@Autowired
	private lateinit var template: TestRestTemplate

	@Test
	@Throws(Exception::class)
	fun shouldValidate_correctNumber_ok() {
		val base = URL("http://localhost:$port/credit-card/validate?creditCard=4561261212345467")
		val response = template.getForEntity(base.toString(), String::class.java)
		assertThat(response.statusCode, equalTo(HttpStatus.OK))
		assertThat(response.body, equalTo("Valid credit card"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldValidate_ncorrectNumber_badRequest() {
		val base = URL("http://localhost:$port/credit-card/validate?creditCard=1234567890")
		val response = template.getForEntity(base.toString(), String::class.java)
		print(response)
		assertThat(response.statusCode, equalTo(HttpStatus.BAD_REQUEST))
		assertThat(response.body, equalTo("Invalid credit card"))
	}

	@Test
	@Throws(Exception::class)
	fun shouldValidate_emptyNumber_badRequest() {
		val base = URL("http://localhost:$port/credit-card/validate?")
		val response = template.getForEntity(base.toString(), String::class.java)
		print(response)
		assertThat(response.statusCode, equalTo(HttpStatus.BAD_REQUEST))
		assertThat(response.body, equalTo("Empty credit card"))
	}

}
