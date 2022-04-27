package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    val pair: Pair<Long, String> = Pair(1L, "Lob")
    println("pair = $pair")

    // fun <K, V> mapOf(pair: Pair<K, V>): Map<K, V>
    // val map: Map<Long, String> = mapOf<Long, String>()
    val map: Map<Long, String> = mapOf(1L to "Lob")
    println("map = $map")

    val map2 = emptyMap<Long, String>()
    val map3 = mapOf<Long, String>()
    println("${map2 == map3}")


}