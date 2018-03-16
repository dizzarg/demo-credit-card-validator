package ru.kadyrovd.credit.card

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoCreditCardValidatorApplication

@RestController
@RequestMapping("/credit-card")
class CreditCardController {

    @Autowired
    lateinit var validator :CreditCardValidator

    @GetMapping("/validate")
    fun validate(@RequestParam("creditCard") creditCard: String?): ResponseEntity<String> {
        if(creditCard.isNullOrEmpty()) {
            return ResponseEntity.badRequest().body("Empty credit card")
        }
        return if (validator.validate(creditCard!!)) {
                ResponseEntity.ok("Valid credit card")
            } else {
                ResponseEntity.badRequest().body("Invalid credit card")
            }
    }
}

@Service
class CreditCardValidator {

    fun validate(creditCard: String): Boolean {
        return creditCard.toCharArray()
                .filter { it.isDigit() }
                .map { it.toInt() - '0'.toInt() }
                .mapIndexed { index, i -> if (index % 2 == 0) i + i else i }
                .map { if (it > 9) it - 9 else it }
                .sum() % 10 == 0
    }
}

fun main(args: Array<String>) {
    runApplication<DemoCreditCardValidatorApplication>(*args)
}
