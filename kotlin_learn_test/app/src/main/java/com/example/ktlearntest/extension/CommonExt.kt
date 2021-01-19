//@file:JvmName("Utils")
package com.example.ktlearntest.extension

/**
 * 扩展函数：Kotlin 能够扩展一个类的新功能而无需继承该类。
 * 例如，你可以为一个你不能修改的来自第三方库中的类编写一个新的函数
 */
fun String.getSpecial(content: String): String {
    return content + "hello world"
}

/**
 * 除了扩展函数，还可以扩展属性：
 * 例如我想实现String和StringBuilder通过属性去直接获得最后字符
 */
val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }


fun main() {
    val result = "aaa".getSpecial("bye-bye")
    println(result)

    val s = "abc"
    println(s.lastChar)

    val sb = StringBuilder("123")
    println(sb.lastChar)

}