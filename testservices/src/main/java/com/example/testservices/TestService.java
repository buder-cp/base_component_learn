package com.example.testservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class TestService extends Service {

    public class MyBinder extends Binder {
        public TestService getService() {
            return TestService.this;
        }
    }

    private MyBinder binder = new MyBinder();

    public int getRandomNumber() {
        return new Random().nextInt();
    }

    @Override
    public void onCreate() {
        Log.e("buder","TestService 执行 onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("buder","TestService 执行 onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("buder","TestService 执行 onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("buder","TestService 执行 onUnbind");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.e("buder","TestService 执行 onDestroy");
        super.onDestroy();
    }
}
