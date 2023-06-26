package com.lob.kotlin.demo.algorithm

fun main(args: Array<String>) {
    val prices: IntArray = intArrayOf(7, 1, 5, 3, 6, 4)
    // val prices: IntArray = intArrayOf(1, 2, 3, 4, 5)
    // val prices: IntArray = intArrayOf(7, 6, 4, 3, 1)
    val profit: Int = maxProfit(prices)
    println("profit = $profit")
}

// i  i+1
// 7, 1 = 0, -6
// 1, 5 = 0, 4
// 5, 3 = 0, -2
// 3, 6 = 0, 3
// 6, 4 = 0, -2

fun maxProfit(prices: IntArray): Int {
    var result = 0
    (1 until prices.size).forEach {
        result += maxOf(0, prices[it] - prices[it - 1])
    }
    return result
}