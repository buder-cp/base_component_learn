package com.cniao5.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val mockPlayer = MockMediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mockPlayer.onCreate()
        MockMediaPlayer2(this)
//        MockMediaPlayer3(this)
        Log.i("MainActivity", "onCreate")

    }

//    override fun onResume() {
//        super.onResume()
//        mockPlayer.onResume()
//        Log.i("MainActivity", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mockPlayer.onPause()
//        Log.i("MainActivity", "onPause")
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mockPlayer.onDestroy()
//        Log.i("MainActivity", "onDestroy")
//    }
}