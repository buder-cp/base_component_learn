package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * 多次startService()，会多次执行onStartCommand()
 *
 * 2021-03-24 10:48:57.447 12458-12458/com.example.service I/System.out: 执行了onCreat()
 * 2021-03-24 10:48:57.448 12458-12458/com.example.service I/System.out: 执行了onStartCommand()
 * 2021-03-24 10:48:59.177 12458-12458/com.example.service I/System.out: 执行了onStartCommand()
 * 2021-03-24 10:48:59.881 12458-12458/com.example.service I/System.out: 执行了onStartCommand()
 * 2021-03-24 10:49:01.390 12458-12458/com.example.service I/System.out: 执行了onDestory()
 */
public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("执行了onCreat()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("执行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("执行了onDestory()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
