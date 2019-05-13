package com.example.instancestatetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String TAG = "buder";

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text);

        //方式一：数据恢复，从K-V中获取销毁之前的存储值
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("hehe");
            Log.e(TAG, "onCreateRestore" + test);
            mTextView.setText(test);
        }
    }

    //在pause/stop之间进行界面上的数据存储，到K-V中
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        String change = mTextView.getText().toString();
        outState.putString("hehe", change);
    }

    //方式二：数据恢复，从K-V中获取销毁之前的存储值
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("hehe");
        Log.e(TAG, "onRestore" + test);
        mTextView.setText(test);
    }
}
