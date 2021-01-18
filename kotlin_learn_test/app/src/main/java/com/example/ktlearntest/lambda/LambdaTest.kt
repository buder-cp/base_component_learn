package com.example.ktlearntest.lambda
//https://github.com/heimashi/kotlin_tips/blob/master/app/src/main/java/com/sw/kotlin/tip15/KotlinTip15.kt

fun main() {
//    test02()
    test03()
    test04()
}

fun test02() {
    /*
    * 定义函数类型
    * */
    val sum = { x: Int, y: Int -> x + y }
    val action = { println(42) }

    val sum2: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val action2: () -> Unit = { println(42) }
}
/*
* 定义对2和3的操作函数
* */
fun twoAndThree(operator: (Int, Int) -> Int) {
    val result = operator(2, 3)
    println("Result:$result")
}

fun test03() {
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }
}

/*
* 函数作为参数，实现String类的字符过滤
* */
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun test04() {
    println("12eafsfsfdbzzsa".filter { it in 'a'..'f' })
}
