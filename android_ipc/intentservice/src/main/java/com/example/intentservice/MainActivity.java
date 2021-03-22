package com.example.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 同一服务只会开启1个工作线程
        // 在onHandleIntent（）函数里，依次处理传入的Intent请求
        // 将请求通过Bundle对象传入到Intent，再传入到服务里

        // 请求1
        //在5.0以后不允许使用隐式Intent方式来启动Service
//        Intent intent = new Intent();
//        intent.setAction("cn.scu.finch");
//        intent.setPackage("com.example.intentservice");
//        Bundle bundle = new Bundle();
//        bundle.putString("taskName", "task1");
//        intent.putExtras(bundle);
//        startService(intent);


        //在5.0以后不允许使用隐式Intent方式来启动Service
        Intent intent = new Intent();
//        ComponentName componentName = new ComponentName(pkgName,serviceName);
        ComponentName componentName = new ComponentName("com.example.intentservice", "com.example.intentservice.myIntentService");
        intent.setComponent(componentName);
        Bundle bundle = new Bundle();
        bundle.putString("taskName", "task1");
        intent.putExtras(bundle);
        startService(intent);

//        // 请求2
//        Intent i2 = new Intent();
//        i2.setAction("cn.scu.finch");
//        i2.setPackage("com.example.intentservice");
//        Bundle bundle2 = new Bundle();
//        bundle2.putString("taskName", "task2");
//        i2.putExtras(bundle2);
//        startService(i2);
//
//        startService(intent);  //多次启动

    }
}