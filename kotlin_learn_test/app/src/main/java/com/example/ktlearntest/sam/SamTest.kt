package com.example.ktlearntest.sam

//https://www.kotlincn.net/docs/reference/fun-interfaces.html
// 注意需用fun 关键字声明
interface Action {
    fun run()
}

fun runAction(a: Action) = a.run()

fun main() {
    // 传递一个对象，OK
    runAction(object : Action{
        override fun run() {
            println("run action")
        }
    })
    /**
     * kotlin 1.3 不支持 SAM
     */
    // 1.4-M1支持SAM,OK
//    runAction {
//        println("Hello, Kotlin 1.4!")
//    }

}
