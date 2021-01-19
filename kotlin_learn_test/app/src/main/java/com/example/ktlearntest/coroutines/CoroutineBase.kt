package com.example.ktlearntest.coroutines

import kotlinx.coroutines.*

fun main() {

    // 方法一，使用 runBlocking 顶层函数
    // 方法一通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的。
    runBlocking {
        delay(2000)
    }

    // 方法二，使用 GlobalScope 单例对象
    // 方法二和使用 runBlocking 的区别在于不会阻塞线程。但在 Android 开发中同样不推荐这种用法，
    // 因为它的生命周期会和 app 一致，且不能取消
    GlobalScope.launch {
        delay(2000)
    }

    // 方法三，自行通过 CoroutineContext 创建一个 CoroutineScope 对象
    // 方法三是比较推荐的使用方法，我们可以通过 context 参数去管理和控制协程的生命周期
    CoroutineScope(Dispatchers.Main).launch {
        delay(2000)
    }
}