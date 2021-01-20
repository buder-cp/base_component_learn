package com.example.ktlearntest.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
//    CoroutineScope(Dispatchers.Main).launch {
//
//    }
    GlobalScope.launch() {
        launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                launch(Dispatchers.IO) {

                }
            }
        }
        withContext(Dispatchers.Main) {

        }
        withContext(Dispatchers.IO) {

        }
        withContext(Dispatchers.Main) {

        }
    }
    GlobalScope.launch() {
        val time = measureTimeMillis {
            /**
             * 串行写法一：
             */
//            val jobOne = withContext(Dispatchers.IO) {
//                jobOne()
//            }
//            println("job one：${jobOne}")
//
//            val jobTwo = withContext(Dispatchers.IO) {
//                jobTwo()
//            }
//            println("job two：${jobTwo}")

            /**
             * 串行写法二：
             */
//            val one = jobOne()
//            val two = jobTwo()
//            println("job The answer is ${one + two}")

            /**
             * 串行写法三：
             */
//            val firstJob = async { jobOne() }
//            val secondJob = async {
//                firstJob.await()//如果第二个Job的开始需要等第一个job的结果
//                jobTwo()
//            }
//            println("firstJob: ${firstJob.await()}, secondJob: ${secondJob.await()}")

            /**
             * 并行
             */
            val one = async { jobOne() }
            val two = async { jobTwo() }
            println("job The answer is ${one.await() + two.await()}")
        }
        println("job 执行耗时：${time}")
    }
    println("job ----------------")
    Thread.sleep(5000)//应为上面的协程代码并不会阻塞掉线程，所以我们这里让线程睡4秒，保证线程的存活，在实际的Android开发中无需这么做
}

private suspend fun jobOne(): Int {
    println("job The answer is ${Thread.currentThread().name}")
    delay(2500)
    println("job The answer is ${Thread.currentThread().name}")
    return 1
}

private suspend fun jobTwo(): Int {
    println("job The answer is ${Thread.currentThread().name}")
    delay(1500)
    println("job The answer is ${Thread.currentThread().name}")
    return 2
}