package com.example.lifecycletest.viewmodel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.lifecycletest.R;

public class TimerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        iniComponent();
    }

    private void iniComponent() {
        final TextView tvTime = findViewById(R.id.tvTime);
        //通过ViewModelProviders得到ViewModel，如果ViewModel不存在就创建一个新的，如果已经存在就直接返回已经存在的
        TimerViewModel timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        timerViewModel.setOnTimeChangeListener(new TimerViewModel.OnTimeChangeListener() {
            @Override
            public void onTimeChanged(final int second) {
                //更新UI界面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText("TIME:" + second);
                    }
                });
            }
        });

        timerViewModel.startTiming();
    }
}
