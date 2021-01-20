package com.example.ktlearntest.inlineFunVSConst

/**
 *
 * 内联函数的错误使用:
 * https://www.jianshu.com/p/ab877fe72b40
 * https://www.jianshu.com/p/989f9f393b2b?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 *
 * 每调用一个方法，都会对应一个栈帧的入栈出栈过程，如果你有一个工具类方法，
 * 在某个循环里调用很多次，那就会对应很多次的栈帧入栈、出栈过程。
 * 这里首先要记住的一点是，栈帧的创建及入栈、出栈都是有性能损耗的
 */
fun test() {
    //多次调用 sum() 方法进行求和运算
    println(sum(1, 2, 3))
    println(sum(100, 200, 300))
    println(sum(12, 34))
    //....可能还有若干次
}

/**
 * 求和计算
 */
fun sum(vararg ints: Int): Int {
    var sum = 0
    for (i in ints) {
        sum += i
    }
    return sum
}

/**
 *我们期望的代码类似这样子的
 *
 * 一般工具类有很多公共方法，我总不能在需要调用这些公共方法的地方，把代码复制一遍吧，内联就是为了解决这一问题
 */
fun test1() {
    var sum = 0
    for (i in arrayOf(1, 2, 3)) {
        sum += i
    }
    println(sum)

    sum = 0
    for (i in arrayOf(100, 200, 300)) {
        sum += i
    }
    println(sum)

    sum = 0
    for (i in arrayOf(12, 34)) {
        sum += i
    }
    println(sum)
}

/**
 * 定义如下的内联函数
 */
inline fun sum1(vararg ints: Int): Int {
    var sum = 0
    for (i in ints) {
        sum += i
    }
    return sum
}