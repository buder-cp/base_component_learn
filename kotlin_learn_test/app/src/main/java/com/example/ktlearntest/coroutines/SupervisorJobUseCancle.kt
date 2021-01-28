package com.example.ktlearntest.coroutines

import kotlinx.coroutines.*

class WorkManager {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    fun doWork1() {
        scope.launch {
            println("doWork1")
        }
    }

    fun doWork2() {
        scope.launch {
            println("doWork2")
        }
    }

    fun cancelAllWork() {
        job.cancel()//以后再起的job无法工作
//        scope.coroutineContext.cancelChildren()//以后再起来的job可以工作
    }
}

fun main() {
    val workManager = WorkManager()
    workManager.doWork1() // (1)
    workManager.doWork2() // (2)
    workManager.cancelAllWork()
    workManager.doWork1() // (3)
}