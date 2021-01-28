package com.example.ktlearntest.coroutines

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val scope0 = this
        // scope0是顶级协程范围。
        scope0.launch {
            val scope1 = this
            // scope1从scope0继承其上下文。它用自己的作业替换Job字段，该作业是scope0中该作业的子级。
            // 它保留了Dispatcher字段，因此启动的协程使用runBlocking创建的调度程序。
            scope1.launch {
                val scope2 = this
                // scope2继承自scope1
            }
        }
    }

    runBlocking(Dispatchers.Main) {
        withContext(Dispatchers.IO) { }
        withContext(Dispatchers.Main) { }
        withContext(Dispatchers.IO) { }
        withContext(Dispatchers.Default) { }
    }

}