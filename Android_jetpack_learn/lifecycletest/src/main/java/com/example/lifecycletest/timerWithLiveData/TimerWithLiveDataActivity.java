package com.example.lifecycletest.timerWithLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lifecycletest.R;

public class TimerWithLiveDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_with_live_data);
        iniComponent();
    }

    private void iniComponent() {
        //通过ViewModelProviders得到ViewModel
        TimerWithLiveDataViewModel timerWithLiveDataViewModel = ViewModelProviders.of(this).get(TimerWithLiveDataViewModel.class);

        //得到ViewModel中的LiveData
        final MutableLiveData<Integer> liveData = (MutableLiveData<Integer>) timerWithLiveDataViewModel.getCurrentSecond();

        //通过LiveData.observe()实现对ViewModel中数据变化的观察
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer second) {
                //更新UI界面
                ((TextView) findViewById(R.id.tvTime)).setText("TIME:" + second);
            }
        });

        findViewById(R.id.btnResetTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过LiveData.setValue()/LiveData.postValue()实现对ViewModel中数据的更新
                liveData.setValue(0);
            }
        });

        timerWithLiveDataViewModel.startTiming();
    }


}
