package com.test.dual_process;

import android.content.Intent;
import android.os.Bundle;

import com.test.dual_process.service.LocalService;
import com.test.dual_process.service.MyJobService;
import com.test.dual_process.service.RemoteService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
        MyJobService.startJob(this);
    }
}
