package com.example.timeconsumingprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton  = findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //耗时操作任务一
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 100000; i++) {
                            System.out.println("test 1");
                        }
                        Toast toast = Toast.makeText(getApplicationContext(),"点击完成", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }, 1000);

                //耗时操作任务二
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 200000; i++) {
                            System.out.println("test 1");
                        }
                    }
                });

            }
        });
    }
}
