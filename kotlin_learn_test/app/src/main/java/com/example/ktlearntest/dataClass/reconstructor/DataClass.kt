package com.example.ktlearntest.dataClass.reconstructor


/**
 * 数据类的要求：
 * 主构造方法需要至少有一个参数；
 * 主构造方法的所有参数需要标记为 val 或 var；
 * 数据类不能是抽象、开放、密封或者内部的；
 */
data class Address(val name: String, val number: Int) {
    var city: String = ""
    fun print() {
        println(city)
    }
}

/**
在主构造函数中有多少个参数，就会依次生成对应的component1,component2,component3……这些函数返回的就是对应字段的值
componentN函数是用来实现解构声明的
拿上面的例子来说，给 name 赋值，其实调用的是 Address.component1()，给 number 赋值其实调用的是component2()函数。
有了这个解构申明，想在一个函数中返回多个结果，就可以申明一个简单的数据类来返回了，然后取值也很方便。
 */
fun main() {
    val address = Address("Android", 1000)
    address.city = "Beijing"
    val (name, city) = address
    println("name:$name city:$city")
}