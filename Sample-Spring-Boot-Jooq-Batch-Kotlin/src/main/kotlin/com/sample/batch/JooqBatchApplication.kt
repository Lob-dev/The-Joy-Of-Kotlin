package com.sample.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqBatchApplication

fun main(args: Array<String>) {
    runApplication<JooqBatchApplication>(*args)
}