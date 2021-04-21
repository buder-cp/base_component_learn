package com.example.lifecycletest;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLocationListener implements LifecycleObserver {
    private String TAG = this.getClass().getName();
    private Activity context;
    private OnLocationChangedListener onLocationChangedListener;

    public MyLocationListener(Activity context, OnLocationChangedListener onLocationChangedListener) {
        this.context = context;
        this.onLocationChangedListener = onLocationChangedListener;
    }

    /**
     * 当Activity 执行onResume()方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startGetLocation() {
        Log.d(TAG, "startGetLocation()");
    }

    /**
     * 当Activity 执行onPause()方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stopGetLocation() {
        Log.d(TAG, "stopGetLocation()");
    }

    /**
     * 当地理位置发生变化时，通过该接口通知调用者
     */
    public interface OnLocationChangedListener {
        void onChanged(double latitude, double longitude);
    }
}
