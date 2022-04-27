package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // Throw Exception
    fun parseIntNumber(string: String): Int {
        var num = 0
        if (string.length !in 1..31) throw NumberFormatException("Not a number: $string")

        string.forEach { character ->
            if (character !in '0'..'1') throw NumberFormatException("Not a number: $character")
            num = num * 2 + (character - '0')
        }
        return num;
    }

    fun greeting(name: String) {
        val message = if (name.isNotEmpty()) "Hello, $name"
        else throw IllegalArgumentException("Empty name")
    }

    // try - catch 구문도 expression 으로 평가, 인식된다
    val num: Int = try {
        args[0].toInt()
    } catch (e: Exception) {
        0
    } finally {
        println("꽑")
    }

}