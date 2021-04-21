package com.example.lifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//参考：Jetpack 之 LifeCycle 组件使用详解
// https://juejin.cn/post/6872935442849726478

public class MainActivity extends AppCompatActivity {

    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLocationListener = new MyLocationListener(this, new MyLocationListener.OnLocationChangedListener() {
            @Override
            public void onChanged(double latitude, double longitude) {
                // 展示收到的位置信息

            }
        });

        // 将观察者与被观察者绑定
        getLifecycle().addObserver(myLocationListener);

    }
}