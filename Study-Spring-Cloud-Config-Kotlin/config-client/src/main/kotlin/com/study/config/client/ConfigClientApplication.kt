package com.study.config.client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ConfigClientApplication

fun main(args: Array<String>) {
    runApplication<ConfigClientApplication>(*args)
}

@RestController
class ValueRestController(
    val environment: Environment
) {
    fun Environment.readValue(): String = environment.getProperty("cnj.message")?: ""

    val log: Logger = LoggerFactory.getLogger(ValueRestController::class.java)

    @EventListener(value = [
        ApplicationReadyEvent::class,
        RefreshScopeRefreshedEvent::class
    ])
    fun onEvent() {
        log.info("new value: ${this.environment.readValue()}")
    }

    @GetMapping("value")
    fun read() = environment.readValue()

}
