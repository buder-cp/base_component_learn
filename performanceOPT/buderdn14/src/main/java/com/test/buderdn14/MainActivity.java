package com.test.buderdn14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.buderdn14.activity.KeepManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeepManager.getInstance().registerKeepReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unRegisterKeepReceiver(this);
    }
}
