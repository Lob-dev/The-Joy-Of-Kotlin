package com.login.social.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
@ConfigurationPropertiesScan
class SampleSocialApplication {

	@GetMapping("/")
	fun greeting() = "Hello!"
}

fun main(args: Array<String>) {
	runApplication<SampleSocialApplication>(*args)
}
