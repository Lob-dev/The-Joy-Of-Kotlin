package com.lob.kotlin.demo.algorithm

import java.util.*

fun main(args: Array<String>) {
    val nums: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val k: Int = 3
    rotate(nums, k)
    println("nums = ${nums.contentToString()}")
}

fun rotate(nums: IntArray, k: Int): Unit {
    val rotation = k % nums.size
    val temp = IntArray(nums.size)
    for (i in nums.indices) {
        temp[(i + rotation) % nums.size] = nums[i]
    }
    for (i in temp.indices) {
        nums[i] = temp[i]
    }
}