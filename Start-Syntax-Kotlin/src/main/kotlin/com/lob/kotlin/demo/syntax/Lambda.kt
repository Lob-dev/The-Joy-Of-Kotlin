package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // 코틀린에서는 람다를 값처럼 다룰 수 있다.
    // 1. 람다는 메소드의 파라미터로 사용할 수 있다.
    // 2. return 값으로 사용할 수 있다.

    val square = { number: Int -> number + number }
    println(square(10))


    val nameAge = { name: String, age: Int -> "my name is $name I'm $age" }
    println(nameAge("Lob", 24))

    val name = "Lob"
    val job = "student"
    val age = 24
    val stringExtend2: String.(String, Int) -> String = { job, age -> "$this is $job and age $age hello!" }
    println(name.stringExtend2(job, age))


    fun <T> ArrayList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    val users: ArrayList<String> = arrayListOf("Lob", "Babo", "Bol")
    users.swap(0, 2)
    println(users)

    val numbers: ArrayList<Int> = arrayListOf(10, 20, 30)
    numbers.swap(0, 2)
    println(numbers)

}