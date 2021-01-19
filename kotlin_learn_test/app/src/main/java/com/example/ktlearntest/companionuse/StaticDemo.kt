package com.example.ktlearntest.companionuse
//https://www.jianshu.com/p/14db81e1576a
/**
 * const 关键字用来修饰常量，且只能修饰 val，不能修饰var
 *
 * Kotlin中并没有static关键字，不过我们可以借助companion object 来实现类方法的目的。
 */
class StaticDemoActivity {
    //静态常量
    companion object {
        //三种类型，const-> public
        const val TYPE_1 = "loanType"
        val TYPE_2 = "loanTitle"
        @JvmStatic
        val TYPE_3 = "loanTitle"

        //静态方法，在java中调用时需要使用 Companion
        fun test() {

        }

        //静态方法，在java调用时无需使用 Companion
        @JvmStatic
        fun test2222() {

        }

    }
}
