package com.example.ktlearntest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ktlearntest.javaInvokeKotlin.joinToString

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        joinToString("aaa", param3 = "ccc", param2 = "ddd")
        joinToString("aaa", "bbb", "ccc", "ddd")
        joinToString("aaa", "ccc", "ddd")
        joinToString(param2 = "aaa")

    }
}