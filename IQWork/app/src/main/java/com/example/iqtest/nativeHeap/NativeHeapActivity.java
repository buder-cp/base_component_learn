package com.example.iqtest.nativeHeap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import com.example.iqtest.R;

public class NativeHeapActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_native_heap);
//    }


    private boolean isActing = false;

    private TextView textView;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 200) {
                long millis = System.currentTimeMillis();
                textView.setText(System.currentTimeMillis() + "");
                textView.setText(millis + "");
                textView.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

                textView.setText(System.currentTimeMillis() + "");
                textView.setText(millis + "");
                textView.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

                textView.setText(System.currentTimeMillis() + "");
                textView.setText(millis + "");
                textView.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

            }
            handler.removeMessages(200);
            handler.sendEmptyMessageDelayed(200, 500);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_heap);

        textView = findViewById(R.id.tv_test);

        handler.sendEmptyMessage(200);

        new Thread() {
            @Override
            public void run() {
                super.run();
                isActing = true;
                while (isActing) {
                    SystemClock.sleep(10);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(System.currentTimeMillis() + "");
                        }
                    });
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        isActing = false;
        super.onDestroy();
    }

}