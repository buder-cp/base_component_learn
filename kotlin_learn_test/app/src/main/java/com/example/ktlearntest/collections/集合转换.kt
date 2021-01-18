package com.example.ktlearntest.collections

fun main() {
//    val colors = listOf("red", "brown", "grey")
//    val animals = listOf("fox", "bear", "wolf")
//    println(colors zip animals)
//
//    val twoAnimals = listOf("fox", "bear")
//    println(colors.zip(twoAnimals))

//    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
//    println(numberSets.flatten())

    val numbers = listOf("one", "two", "three", "four")

    println(numbers)
    println(numbers.joinToString())

    val listString = StringBuffer("The list of numbers: ")
    numbers.joinTo(listString)
    println(listString)
}