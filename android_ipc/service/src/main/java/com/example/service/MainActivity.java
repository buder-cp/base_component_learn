package com.example.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = findViewById(R.id.startService);
        stopService = findViewById(R.id.stopService);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //点击启动Service
            case R.id.startService:
                //构建启动服务的Intent对象
                Intent startIntent = new Intent(this, MyService.class);
                //调用startService()方法-传入Intent对象,以此启动服务
                startService(startIntent);
                break;

            //点击停止Service
            case R.id.stopService:
                //构建停止服务的Intent对象
                Intent stopIntent = new Intent(this, MyService.class);
                //调用stopService()方法-传入Intent对象,以此停止服务
                stopService(stopIntent);
                break;

            default:
                break;

        }
    }
}