package com.test.opensourceframework;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.test.opensourceframework.dagger2.DaggerMainComponent;
import com.test.opensourceframework.dagger2.Flower;
import com.test.opensourceframework.dagger2.MainModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

//    @Inject
//    Cat cat;

    @Inject
    Flower flower;

    @BindView(R.id.text1)
    TextView textView;

    @OnClick(R.id.text1)
    public void showToast() {
        Toast.makeText(this, "click butterKnife", Toast.LENGTH_SHORT).show();

        jumpActivity();
    }

    private void jumpActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        textView.setText("butterKnife");

        EventBus.getDefault().register(this);

//        daggerPrint();
        dagger2Print();

    }

    private void dagger2Print() {
        DaggerMainComponent.builder()
                // 设置 MainModule 对象
                .mainModule(new MainModule())
                .build()
                .inject(this);

        Log.e("flower", flower.toString());
    }


    private void daggerPrint() {

//        DaggerMainComponent2.builder()
//                .build()
//                .inject(this);
//
//        Log.e("dagger:", cat.toString());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        textView.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
