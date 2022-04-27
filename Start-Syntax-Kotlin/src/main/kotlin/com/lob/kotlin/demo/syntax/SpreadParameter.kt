package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // vararg 라는 키워드는 파라미터로 배열을 받을 수 있다는 것을 나타낸다.
    // 해당 키워드는 함수에서 하나의 파라미터에 대해서만 사용 가능하다.
    fun max(vararg numbers: Int): Int {
        var large = Int.MIN_VALUE
        for (number in numbers) {
            large = if (number > large) number else large
        }
        return large
    }

    println(max(1, 5, 6, 7, 20));
    println(max(1, 5, 2))


    fun greetMany(msg: String, vararg names: String) {
        println("$msg ${names.joinToString(", ")}")
    }
    greetMany("Hello", "Lob", "Bol", "Lbo")

    //fun greetMany2(vararg msg: String, vararg names: String) {}
    // 이런 경우 에러가 발생한다. 이를 해결하기 위해서는
    fun greetMany2(msg: Array<String>, vararg names: String) {}
    // 이렇게 하나의 인자에 대해서는 명시적으로 Array 로 나타내야 한다.

    // 하지만 배열 자체를 인자로 넘길 수는 없다.
    val values = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    //println(max(values))

    // 이러한 경우 스프레드 연산자 "*"를 통해 배열의 값을 추출하여 다중 인자로 넘길 수 있다.
    println(max(*values))

    // 혹은 이렇게 처리할 수 있다.
    println(max(*listOf(1, 4, 18, 12).toIntArray()))

}