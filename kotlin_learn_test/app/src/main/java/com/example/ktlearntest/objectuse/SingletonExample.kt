package com.example.ktlearntest.objectuse

/**
 * 语法含义：将类的声明和定义该类的单例对象结合在一起（即通过object就实现了单例模式）
 */
object RepositoryManager{
    @JvmStatic
    fun method(){
        println("I'm in object declaration")
    }
}