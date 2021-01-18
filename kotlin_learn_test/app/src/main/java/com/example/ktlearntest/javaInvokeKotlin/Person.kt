package com.example.ktlearntest.javaInvokeKotlin

data class Person(
    /**
     * 在 kotlin中我们使用的数据类即 data class 是不需要指定getter和setter的，可以直接通过字段名来访问它们。
     * 但是如果是在Java文件中调用data class依旧是需要使用getter和setter方法进行调用
     */
    @JvmField var name: String,
    @JvmField var age: Int
)