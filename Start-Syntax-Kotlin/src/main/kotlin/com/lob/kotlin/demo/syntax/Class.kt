package com.lob.kotlin.demo.syntax

// 코틀린 클래스는 기본적으로 public 이며, final 한 구조를 가진다.
// public final class Class { ... }
class Class(fullName: String) {
    private val firstName: String
    private val familyName: String

    fun sayFullName(): Unit = println("$firstName $familyName")

    // init
    init {
        val names: List<String> = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

class Class2(val firstName: String, val lastName: String = "")


class MultipleConstructor {
    private val name: String
    private val age: String

    constructor(name: String, age: String) {
        this.name = name
        this.age = age
    }

    constructor(name: String) {
        this.name = name
        this.age = ""
    }

}


// nested class
class Class3(private val id: Id, private val age: Int) {

    inner class Id(val firstName: String, val lastName: String) {
        fun nameSake(class3: Class3) = class3.id.firstName == firstName
    }

    fun showMe() = println("${id.firstName} ${id.lastName}, $age")
}


// internal 키워드는 해당 클래스가 정의된 모듈 안에서만 해당 클래스에 접근 가능함을 이야기한다. 이는 자바의 package private 를 대체한다.
internal class Clazz(val properties: String)

// 클래스를 상속가능하게 하려면 open 키워드를 사용한다.
open class Clasz(val properties: String)