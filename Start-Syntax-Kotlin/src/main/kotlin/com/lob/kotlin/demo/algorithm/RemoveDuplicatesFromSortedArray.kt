package com.lob.kotlin.demo.algorithm

/**
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
 */
fun main(args: Array<String>) {
    // arrayOf(1, 1, 2)
    val nums: Array<Int> = arrayOf(1,1,2)

    // arrayOf(1, 2)
    var expectedNums: Array<Int> = arrayOf(1,2)

    val removeCount: Int = removeDuplicates(nums)
    println("removeCount = $removeCount")
    println("nums = ${nums.size}")
    println("nums.contentDeepEquals(expectedNums) = ${nums.contentDeepEquals(expectedNums)}")
}

fun removeDuplicates(nums: Array<Int>): Int {
    var index = if (nums.isNotEmpty()) 1 else 0
    for (i in 1 until nums.size) {
        if (nums[i] != nums[i-1]) {
            nums[index] = nums[i]
            index += 1
        }
    }
    return index
}