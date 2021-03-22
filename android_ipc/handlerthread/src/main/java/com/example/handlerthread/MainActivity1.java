package com.example.handlerthread;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //Callback 运行于子线程
    private class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            //在此可以进行耗时操作
            //如果需要更新UI，则需要通过主线程的Handler来完成
            Log.e(TAG, "ChildCallback 当前线程名：" + Thread.currentThread().getName() + "   " + "当前线程ID：" + Thread.currentThread().getId());
            Log.e(TAG, "耗时任务开始");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "耗时任务结束");
            //通知界面更新UI
            uiHandler.sendEmptyMessage(1);
            return false;
        }
    }

    //运行于主线程的Handler，用于更新UI
    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG, "uiHandler 当前线程名：" + Thread.currentThread().getName() + "   " + "当前线程ID：" + Thread.currentThread().getId());
            Toast.makeText(MainActivity1.this, "耗时操作完成", Toast.LENGTH_LONG).show();
        }
    };

    //用于向子线程发布耗时任务的Handler
    private Handler childThreadHandler;

    private HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        childThreadHandler = new Handler(handlerThread.getLooper(), new ChildCallback());
        Log.e(TAG, "onCreate 当前线程名：" + Thread.currentThread().getName() + "   " + "当前线程ID：" + Thread.currentThread().getId());
    }

    public void startTask(View view) {
        childThreadHandler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
        childThreadHandler.removeCallbacksAndMessages(null);
    }
}
