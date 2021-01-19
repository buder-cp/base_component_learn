package com.example.ktlearntest.javaInvokeKotlin

data class Person(
    /**
     * 在 kotlin中我们使用的数据类即 data class 是不需要指定getter和setter的，可以直接通过字段名来访问它们。
     * 但是如果在Java文件中调用data class属性字段，这时需要添加 @JvmField
     *
     * https://zhuanlan.zhihu.com/p/95058022
     * 另外，如果我们想在Java中调用setName的时候修改这个属性名称不叫setName，这里我们需要使用@set:JvmName 注解。
     * 同理修改getName使用@get:JvmName
     * 需要注意的是，指定了@set:JvmName或者@get:JvmName注解后不需要在指定@JvmField了。
     */
    @JvmField var name: String,
    @JvmField var age: Int,
    @set:JvmName("getTomPhone")
    @get:JvmName("setJerryPhone")
    var phone: String
) {
    lateinit var address: String

    companion object {
        @JvmStatic
        val sex: String = "man"
    }
}