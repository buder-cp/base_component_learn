package com.cniao5.lifecycle

import android.media.MediaPlayer
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.*
import kotlin.concurrent.thread

/**
 * 用于演示的，无生命周期感知的 media player
 */
class MockMediaPlayer {

    private lateinit var player: MediaPlayer

    fun onCreate() {
        player = MediaPlayer()
        Log.d("MockMediaPlayer", "onCreate")
    }

    fun onResume() {
        thread {
            SystemClock.sleep(3000)//模拟阻塞3s
            player.start()
            Log.d("MockMediaPlayer", "onResume")
        }
    }

    fun onPause() {
        player.stop()
        Log.d("MockMediaPlayer", "onPause")
    }

    fun onDestroy() {
        player.release()
        Log.d("MockMediaPlayer", "onDestroy")
    }

}

class MockMediaPlayer2(private val owner: LifecycleOwner) : LifecycleObserver {

    private lateinit var player: MediaPlayer

    init {
        //关联生命周期的观察者
        owner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        player = MediaPlayer()
        Log.d("MockMediaPlayer2", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        thread {

            SystemClock.sleep(3000)//模拟阻塞1s
            if (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                player.start()
                Log.d("MockMediaPlayer2", "onResume")
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        player.stop()
        Log.d("MockMediaPlayer2", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        player.release()
        Log.d("MockMediaPlayer2", "onDestroy")
    }

}

class MockMediaPlayer3(private val owner: LifecycleOwner) : LifecycleEventObserver {

    private lateinit var player: MediaPlayer

    init {
        //关联生命周期的观察者
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("MockMediaPlayer3", "onCreate")
                player = MediaPlayer()
            }
            Lifecycle.Event.ON_START, Lifecycle.Event.ON_RESUME -> {
                if (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    player.start()
                    Log.d("MockMediaPlayer3", "onResume")
                }
            }
            Lifecycle.Event.ON_PAUSE, Lifecycle.Event.ON_STOP -> {
                player.stop()
                Log.d("MockMediaPlayer3", "onPause")
            }
            Lifecycle.Event.ON_DESTROY -> {
                player.release()
                Log.d("MockMediaPlayer3", "onDestroy")
            }
            Lifecycle.Event.ON_ANY -> Log.d("MockMediaPlayer3", "onAny")
        }
    }

}