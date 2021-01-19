package com.example.ktlearntest.extension

fun main() {
    /**
     * 以闭包形式返回，返回值为最后一行的值或者指定的return的表达式，在run函数中可以直接访问实例的公有属性和方法。
     */
//    val str = "a"
//    val res = str.run{
//        // this 指向 "a" it没有指向
//        // 可以直接访问对象得属性
//        println(length)
//        1 // 最后一行返回值为1
//    }


//    val str1 = "aa"
//    val res1 = str1.let{
//        // this 指向当前class
//        // it 指向a
//        println(it.length)
//        it.replace("a", "vv")
//        "bb" // 返回值"let"
//    }
//    println(res1)

    /**
     * apply函数的作用是：调用某对象的apply函数，在函数范围内，可以任意调用该对象的任意方法，并返回该对象
     *
     * 从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，
     * run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身。
     */
    val str2 = "a"
    val res2 = str2.apply{
        // this指向str it 无指向
        println(this.length)
        this.replace("a", "ddd")
        "ams" // 返回值无效
    }
    println(res2.length)
}