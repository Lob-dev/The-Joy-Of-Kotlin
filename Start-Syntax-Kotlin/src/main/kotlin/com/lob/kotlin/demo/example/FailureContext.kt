package com.lob.kotlin.demo.example

import java.util.function.Supplier

interface FailureHandler {
    fun process(exception: Exception): Boolean
    fun handle(exception: Exception)
}

class FailureContext {

    fun onContext(handler: FailureHandler, process: Supplier<*>) {
        try {
            process.get()
        } catch (exception: Exception) {
            if (handler.process(exception)) {
                return
            } else throw exception
        }
    }
}

class NaverExceptionHandler : FailureHandler {

    val exceptionMap: Map<String, String> = mapOf()

    override fun process(exception: Exception): Boolean {
        handle(exception)
        return true
    }

    override fun handle(exception: Exception) {
        TODO("Not yet implemented")
    }
}

fun main(args: Array<String>) {

    FailureContext().onContext(NaverExceptionHandler()) {

    }
}