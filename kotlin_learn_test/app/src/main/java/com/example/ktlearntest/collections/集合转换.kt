package com.example.ktlearntest.collections

fun main() {
    //不可变集合
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)
//
//    val twoAnimals = listOf("fox", "bear")
//    println(colors.zip(twoAnimals))

//    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
//    println(numberSets.flatten())

//    val numbers = listOf("one", "two", "three", "four")
//
//    println(numbers)
//    println(numbers.joinToString())
//
//    val listString = StringBuffer("The list of numbers: ")
//    numbers.joinTo(listString)
//    println(listString)

    /**
     * 可变集合
     */
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    numbers.removeAt(1)
    numbers[0] = 0
    println(numbers)
}

fun collectionSort() {
    val numbers = mutableListOf(1, 2, 3, 4)
    //随机排列元素
    numbers.shuffle()
    println(numbers)
    numbers.sort()//排序，从小打到
    numbers.sortDescending()//从大到小
    println(numbers)
    //定义一个Person类，有name 和 age 两属性
    data class Language(var name: String, var score: Int)

    val languageList: MutableList<Language> = mutableListOf()
    languageList.add(Language("Java", 80))
    languageList.add(Language("Kotlin", 90))
    languageList.add(Language("Dart", 99))
    languageList.add(Language("C", 80)) //使用sortBy进行排序，适合单条件排序
    languageList.sortBy { it.score }
    println(languageList) //使用sortWith进行排序，适合多条件排序
    languageList.sortWith(compareBy(         //it变量是lambda中的隐式参数
        { it.score }, { it.name })
    )
    println(languageList)
}