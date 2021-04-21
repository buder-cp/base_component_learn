package com.example.lifecycletest.service;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyServiceObserver implements LifecycleObserver {

    private static String TAG = "MyServiceObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void initVideo() {
        Log.d(TAG,"initVideo");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void pausePlay() {
        Log.d(TAG,"stopPlay");
    }

}
