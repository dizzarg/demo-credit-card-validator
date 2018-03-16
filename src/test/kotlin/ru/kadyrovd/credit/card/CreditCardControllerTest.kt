package ru.kadyrovd.credit.card

import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class CreditCardControllerTest {

	@Autowired
	private lateinit var mvc: MockMvc

	@Test
	@Throws(Exception::class)
	fun shouldValidate_correctNumber_ok() {
		mvc.perform(MockMvcRequestBuilders.get("/credit-card/validate?creditCard=4561261212345467")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
				.andExpect(content().string(equalTo("Valid credit card")))
	}

	@Test
	@Throws(Exception::class)
	fun shouldValidate_ncorrectNumber_badRequest() {
		mvc.perform(MockMvcRequestBuilders.get("/credit-card/validate?creditCard=1234567890")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest)
				.andExpect(content().string(equalTo("Invalid credit card")))
	}

	@Test
	@Throws(Exception::class)
	fun shouldValidate_emptyNumber_badRequest() {
		mvc.perform(MockMvcRequestBuilders.get("/credit-card/validate")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest)
				.andExpect(content().string(equalTo("Empty credit card")))
	}

}
