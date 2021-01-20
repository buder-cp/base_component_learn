package com.example.ktlearntest.inlineFunVSConst

//https://zhuanlan.zhihu.com/p/224965169
/**
 * 编译期常量：
 * java中：
 * java中这个变量需要是 final 的，类型只能是字符串或者基本类型，而且这个变量需要在声明的时候就赋值；
 * 这样一来，程序结构就变简单了，编译器和 JVM 也方便做各种优化
 *
 * kotlin中：
 * 这种编译时常量，到了 Kotlin 里有了一个专有的关键字，叫 const：
 * 一个变量如果以 const val 开头，它就会被编译器当做编译时常量来进行内联式编译
 *
 */

const val NAME = "hehe"

val AGE = 23