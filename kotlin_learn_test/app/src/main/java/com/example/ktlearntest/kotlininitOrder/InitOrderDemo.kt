package com.example.ktlearntest.kotlininitOrder

//https://www.kotlincn.net/docs/reference/classes.html
/**
 * 初始化块中的代码实际上会成为主构造函数的一部分。委托给主构造函数会作为次构造函数的第一条语句，
 * 因此所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行。即使该类没有主构造函数，这种委托仍会隐式发生
 */
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    /**
     * 打印结果：
    First property: hello
    First initializer block that prints hello
    Second property: 5
    Second initializer block that prints 5
     */
}

fun main() {
    val initOrder = InitOrderDemo("hello")
    println(initOrder.toString())

//    val derived = Derived("hello", "yes")
//    println(derived.toString())
}

open class Base(val name: String) {

    init {
        println("Initializing Base")
    }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
    name: String,
    private val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init {
        println("Initializing Derived")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}