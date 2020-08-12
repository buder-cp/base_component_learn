package com.test.opensourceframework;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @OnClick(R.id.btn)
    public void toMainActivity() {
        EventBus.getDefault().post(new MessageEvent("改变后的值"));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }
}
