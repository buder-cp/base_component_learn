package com.example.ktlearntest.sam

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView

//https://mp.weixin.qq.com/s/D2utXi5iWY5o3Pi_zZJscQ

class SamSelfTest : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //传统方式
//        Handler().post(Runnable {
//            Thread.sleep(1000)
//        })
        //优化2
//        Handler().post({
//            Thread.sleep(1000)
//        })
        //优化2
//        Handler().post {
//            Thread.sleep(1000)
//        }

        val textView = TextView(applicationContext)
        //传统方式
//        textView.setOnClickListener(View.OnClickListener {
//            // handle click
//        })
        //lambda的方式
//        textView.setOnClickListener({view ->
//            // handle click
//        })

        //函数如果没有其他参数括号可以省略
        textView.setOnClickListener{
            // handle click
        }


    }
}
