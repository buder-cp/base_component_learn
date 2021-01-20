package com.example.ktlearntest.dataClass

/**
 * 编译器自动从主构造函数中声明的所有属性导出以下成员：
equals()/hashCode() 对；
toString() 格式是 "User(name=John, age=42)"；
componentN() 函数 按声明顺序对应于所有属性；
copy() 函数
 */

/**
 * data关键词来声明一个数据类，除了会自动实现get set，还会自动生成equals hashcode toString
 */

data class DataClassTest(val name: String = "11", val email: String = "22")

/**
 * Kotlin会为类的参数自动实现get set方法
 */
class User(val name: String, val age: Int, val gender: Int, var address: String)