package com.example.ktlearntest.extension

fun main() {
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

    val str2 = "a"
    val res2 = str2.apply{
        // this指向str it 无指向
        println(this.length)
        this.replace("a", "ddd")
        "ams" // 返回值无效
    }
    println(res2.length)
}