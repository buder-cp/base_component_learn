package com.example.ktlearntest.coroutines

import kotlinx.coroutines.*

val job: Job = SupervisorJob()
val scope = CoroutineScope(Dispatchers.Default + job)

suspend fun doWork1(): Deferred<Int> = scope.async {
    delay(10)
    111
}

suspend fun doWork2(): Deferred<Int> = scope.async {
    delay(1000)
    throw ArithmeticException()
    121
}

fun main() {
    runBlocking {
        var work1 = 0
        var work2 = 0

        work1 = doWork1().await()
        println("work1 result $work1")

        try {
            work2 = doWork2().await()
            println("work2 result $work2")
        } catch (e: Exception) {
            println("dowork2 catch $e")
        }
        println("final: ${work1 + work2}")
    }
}