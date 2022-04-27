package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // Destructuring Declaration
    // 객체의 상태를 변수에 대입하여 사용할 수 있는 방식
    // data class 는 상태 변수에 대해서 메서드가 선언되어 있기에 별도 처리없이 사용할 수 있다.
    data class Hello(val name: String, val age: Int)

    val hellos = arrayListOf(Hello("Lob", 24), Hello("Bol", 42))
    for ((name, age) in hellos) println("$name, $age")

    println("----------")

    // 일반 class 에서 해당 기능을 사용하기 위해서는 operator fun 함수를 내부적으로 선언하여야 한다.
    // 함수 명은 관례적으로 component{N} 으로 선언되어야 한다. 여기서 N은 변수에 대입되는 순서이다.
    class Person(val name: String, val age: Int) {
        operator fun component1(): String = name
        operator fun component2(): Int = age
    }

    val person = Person("Lob", 24)
    val (name, age) = person
    println("$name, $age")

}