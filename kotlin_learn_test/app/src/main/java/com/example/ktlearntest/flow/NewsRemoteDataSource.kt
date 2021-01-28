package com.example.ktlearntest.flow

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun fun1() {
    GlobalScope.launch {
        print(myFlow.count())
    }
    Thread.sleep(1000)
}

val myFlow = flow {
    // GlobalScope.launch { // is prohibited
    // launch(Dispatchers.IO) { // is prohibited
    // withContext(CoroutineName("myFlow")) // is prohibited
    emit(1) // OK
    coroutineScope {
        emit(2) // OK -- still the same coroutine
    }
}

//        flow.collect {
//            println(it)
//        }

fun main() {
//    base()
//    zip()
//    combine()
//    conflate()
    debounce()
}

fun debounce() {
    runBlocking {
        flow {
            emit(1)
            delay(90)
            emit(2)
            delay(90)
            emit(3)
            delay(1010)
            emit(4)
            delay(10)
            emit(5)
            delay(100)
            emit(6)
            delay(1000)
            emit(7)
        }.debounce(1000).collect{
            print(it)
        }
    }
}

fun conflate() {
    runBlocking {
        val useTime = measureTimeMillis {
            val flow = flow {
                for (i in 1..30) {
                    delay(100)
                    emit(i)
                }
            }
            val result = flow.conflate().onEach { delay(1000) }
            println(result.toList())
        }
        println(useTime)
    }
}

fun combine() {
    runBlocking {
        val flow = flowOf(1, 2, 3).onEach { delay(10) }
        val flow2 = flowOf("a", "b", "c").onEach { delay(15) }
        flow.combine(flow2) { i, s -> i.toString() + s }.collect {
            println(it) // Will print "1a 2a 2b 2c"
        }
    }
}

fun zip() {
    runBlocking {
        val flow = flowOf(1, 2, 3).onEach { delay(1000) }
        val flow2 = flowOf("a", "b", "c", "d").onEach { delay(15) }
        flow.zip(flow2) { i, s -> i.toString() + s }.collect {
            println(it) // Will print "1a 2b 3c"
        }
    }
}

fun base() {
    fun loadData() = flow {
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    fun getNewData(value: Int): String {
        return "new data: ${value + 1}"
    }

    runBlocking {
        val flow = loadData()
        flow.filter {
            it > 1
        }.map {
            getNewData(it)
        }.flowOn(Dispatchers.IO).collect {
            println(it)
        }
    }
}





















