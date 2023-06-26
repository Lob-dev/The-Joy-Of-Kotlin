package com.lob.kotlin.demo.algorithm

fun main(args: Array<String>) {
    val n: Int = 7
    val k: Int = 3

    // 1, 2, 3, 4, 5, 6, 7
    val arr: IntArray = IntArray(7) { index -> index + 1 }
    val builder: StringBuilder = StringBuilder()

    var step = 0
    val result: IntArray = IntArray(7)
    for (index in 0 until n step 1) {
        step += k - 1
        step %= arr.size
        val value: Int = arr[step]
        result[index] = value
    }

    builder.append("< ")
    result.forEachIndexed { index, it -> if (arr.size-1 != index) builder.append("$it, ") else builder.append("$it >") }
    println("result = $builder")
}