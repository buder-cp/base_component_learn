package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
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


/**
 * 启动服务多次点击：多次执行onStartCommand()
 * 绑定服务多次点击：只执行一次onBind()，onServiceConnected()也只会执行一次
 * 停止服务点击：如果没有点击解绑，则不会执行onDestory()
 * 解绑服务点击：执行onUnbind() -> onDestory()
 *
 * 2021-03-24 11:01:33.037 16381-16381/com.example.service I/System.out: 执行了onCreat()
 * 2021-03-24 11:01:33.038 16381-16381/com.example.service I/System.out: 执行了onStartCommand()
 * 2021-03-24 11:01:34.600 16381-16381/com.example.service I/System.out: 执行了onStartCommand()
 * 2021-03-24 11:01:35.201 16381-16381/com.example.service I/System.out: 执行了onStartCommand()
 *
 * 2021-03-24 11:01:37.064 16381-16381/com.example.service I/System.out: isBindServiceSuccess:true
 * 2021-03-24 11:01:37.073 16381-16381/com.example.service I/System.out: 执行了onBind()
 * 2021-03-24 11:01:37.077 16381-16381/com.example.service I/System.out: Service关联了Activity,并在Activity执行了Service的方法
 * 2021-03-24 11:01:51.216 16381-16381/com.example.service I/System.out: isBindServiceSuccess:true
 * 2021-03-24 11:01:53.801 16381-16381/com.example.service I/System.out: isBindServiceSuccess:true
 *
 * 2021-03-24 11:02:00.925 16381-16381/com.example.service I/System.out: 执行了onUnbind()
 * 2021-03-24 11:02:00.926 16381-16381/com.example.service I/System.out: 执行了onDestory()
 */
public class MyService extends Service {

    private MyBinder mBinder = new MyBinder();

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
        System.out.println("执行了onBind()");
        //返回实例
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("执行了onUnbind()");
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder {
        public void service_connect_Activity() {
            System.out.println("Service关联了Activity,并在Activity执行了Service的方法");
        }
    }

}
