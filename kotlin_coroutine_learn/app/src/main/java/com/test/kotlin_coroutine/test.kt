package com.test.kotlin_coroutine

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            println("job: I'm sleeping $it 。。。")
            delay(500L)
        }
    }

    delay(2300L)
    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
    job.cancel()
    println("main: Now I can quit")

}