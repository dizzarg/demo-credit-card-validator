package ru.kadyrovd.credit.card

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CreditCardValidatorTest {

	@Autowired
	private lateinit var creditCardValidator: CreditCardValidator

	@Test
	fun shouldValidate_correctNumber_success() {
		val response = creditCardValidator.validate("4561261212345467")
		assertThat(response, equalTo(true))
	}

	@Test
	fun shouldValidate_incorrectNumber_failure() {
		val response = creditCardValidator.validate("1234567890")
		assertThat(response, equalTo(false))
	}

}
