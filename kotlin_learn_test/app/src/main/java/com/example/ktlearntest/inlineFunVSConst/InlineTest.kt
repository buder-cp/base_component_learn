package com.example.ktlearntest.inlineFunVSConst

//https://www.jb51.net/article/143393.htm
/**
内联函数：
 */
inline fun doSomething(action: () -> Unit) {
    println("Before doSomething...")
    action()
    println("After doSomething...")
}

fun main() {
    doSomething {
        println("hello world")
    }

    val action = {
        println("https://www.baidu.com")
    }
    doSomething(action)
}


inline fun doSomething(action: () -> Unit, noinline secretAction: () -> Unit) {
    action()
    doSomethingSecret(secretAction)
}

fun doSomethingSecret(secretAction: () -> Unit) {
}

/**
 * 内联函数的意义：
 * https://zhuanlan.zhihu.com/p/224965169
 *
 * 使用高阶函数会给运行时带来一些坏处：每个函数都是一个对象，捕获闭包（如：访问函数体内的变量），内存分配（函数对象或Class），虚拟调用引入的运行过载。 使用内联Lambda表达式在多数情况下可以消除这种过载。
 *
 * 让变量内联用的是 const；而除了变量，Kotlin 还增加了对函数进行内联的支持
 * 虽然同为内联，inline 关键字的作用和目的跟 const 是完全不同的。
 *
 * 就是用一个 JVM 对象来作为函数类型的变量的实际载体，让这个对象去执行实际的代码。
 * 也就是说，在我对代码做了刚才那种修改之后，程序在每次调用 hello() 的时候都会创建一个对象来执行 Lambda 表达式里的代码，
 * 虽然这个对象是用一下之后马上就被抛弃，但它确实被创建了。
 *
 *
 * 刚才我说了，inline 关键字不止可以内联自己的内部代码，还可以内联自己内部的内部的代码，意思是什么呢，
 * 就是你的函数在被加了 inline 关键字之后，编译器在编译时不仅会把函数内联过来，
 * 且会把它内部的函数类型的参数——那就是那些 Lambda 表达式——也内联过来。
 * 换句话说，这个函数被编译器贴过来的时候是完全展开铺平的
 *
 * 经过这种优化，是不是就避免了函数类型的参数所造成的临时对象的创建了？
 * 这样的话，是不是就不怕在循环或者界面刷新这样的高频场景里调用它们了？
 *
 *
 * inline 是用来优化的吗？是，但你不能无脑使用它，你需要确定它可以带来优化再去用它，否则可能会变成负优化
 * 如果你写的是高阶函数，会有函数类型的参数，加上 inline 就对了。
 */
