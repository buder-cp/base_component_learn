package com.example.ktlearntest.extension

/**
 * 扩展函数：Kotlin 能够扩展一个类的新功能而无需继承该类。
 * 例如，你可以为一个你不能修改的来自第三方库中的类编写一个新的函数
 */
fun String.getSpecial(content: String): String {
    return content + "hello world"
}

fun main() {
    val result = "getSpecial".getSpecial("bye-bye")
    println(result)
}