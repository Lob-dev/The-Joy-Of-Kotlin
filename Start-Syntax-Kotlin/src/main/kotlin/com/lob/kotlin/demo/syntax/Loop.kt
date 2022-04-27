package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // until 는 증가할 범위를 만들어낸다. (끝의 값을 포함하지 않는다.)  0 ~ until(n-1)
    // step 은 증가하는 값을 만들어낸다.
    // 0, 2, 4, 6, 8
    for (i in 0 until 10 step 2) println(i)

    // 위와 아래의 for 은 동일하다.
    for (i in 0.until(10).step(2)) println(i)

    println("----------")

    // .. 도 증가할 범위를 만들어내며, 끝의 값을 포함한다.
    // 0, 2, 4, 6, 8, 10
    val range = 0..10 step 2
    for (i in range) println(i)

    println("----------")

    val users = arrayListOf("lob", "jay", "mont")
    for (name in users) println(name)

    println("----------")

    // index 와 name 을 같이 사용하는 방법
    for ((index, name) in users.withIndex()) println("$index - $name")

    println("----------")

    var index = 0
    while (index < 10) {
        println(index)
        index++
    }

    println("----------")

    // 반복문 횟수에 따라 값을 감소시키는 down to 연산자도 존재한다.
    for (i in 5.downTo(1)) println("$i")

    println("----------")

    for (i in 5 downTo 1) println("$i")

    println("----------")

    for (i in 10 downTo 0 step 2) println("$i")

    println("----------")

    // 필터를 정의하여 조건식에 따라서 내부로 넘길 값을 필터링할 수 있다.
    for (i in (1..10).filter { it % 3 == 0 || it % 5 == 0 }) {
        println("$i")
    }

    // labeled loop
    fun indexOf(subArray: IntArray, array: IntArray): Int {
        outer@ for (i in array.indices) {
            for(j in subArray.indices) {
                if (subArray[j] != array[i + j]) continue@outer
            }
            return i
        }
        return -1
    }

}