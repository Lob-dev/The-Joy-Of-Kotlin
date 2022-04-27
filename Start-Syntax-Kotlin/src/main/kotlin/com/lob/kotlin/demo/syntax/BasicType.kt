package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {


    // Basic type
    // 기본적으로 Basic Type 은 Representation 을 통해 JVM 의 Primitive Type 으로 표현된다.
    // 하지만 Null 이 들어올 수 있는 상태 변수나 ? 이 명시된 경우에는 Reference Type 으로 사용한다.

    val double: Double = 1.1
    val float: Float = 1.1f
    val long: Long = 1L
    val int: Int = 1
    val short: Short = 1
    val byte: Byte = 1
    val boolean: Boolean = true
    val char: Char = 'c'


    // Basic + ?
    // null 을 허용할 수 있음을 컴파일러에게 알림으로써 Reference Type 의 상태 변수로 사용한다.

    val double2: Double? = null
    val float2: Float? = null
    val long2: Long? = null
    val int2: Int? = null
    val short2: Short? = null
    val byte2: Byte? = null
    val boolean2: Boolean? = null
    val char2: Char? = null

}