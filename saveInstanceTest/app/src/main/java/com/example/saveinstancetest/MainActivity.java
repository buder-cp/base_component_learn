package com.example.saveinstancetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//************************调用 onSaveInstanceState() ***************************************
/**
 * 按home键：
 * 2021-01-29 16:30:29.493 27040-27040/com.example.saveinstancetest D/buder: onCreate
 * 2021-01-29 16:30:29.494 27040-27040/com.example.saveinstancetest D/buder: onStart
 * 2021-01-29 16:30:29.496 27040-27040/com.example.saveinstancetest D/buder: onResume
 * 2021-01-29 16:30:35.313 27040-27040/com.example.saveinstancetest D/buder: onPause
 * 2021-01-29 16:30:35.340 27040-27040/com.example.saveinstancetest D/buder: onStop
 * 2021-01-29 16:30:35.343 27040-27040/com.example.saveinstancetest E/buder: onSaveInstanceState
 *
 */

/**
 * 从显示onResume时，进入到最近任务：
 * 2021-01-29 16:31:30.103 27040-27040/com.example.saveinstancetest D/buder: onResume
 * 2021-01-29 16:31:43.253 27040-27040/com.example.saveinstancetest D/buder: onPause
 * 2021-01-29 16:31:43.274 27040-27040/com.example.saveinstancetest D/buder: onStop
 * 2021-01-29 16:31:43.275 27040-27040/com.example.saveinstancetest E/buder: onSaveInstanceState
 */

/**
 * 从显示onResume时，按电源键：
 * 2021-01-29 16:33:29.434 27040-27040/com.example.saveinstancetest D/buder: onResume
 * 2021-01-29 16:33:33.045 27040-27040/com.example.saveinstancetest D/buder: onPause
 * 2021-01-29 16:33:33.049 27040-27040/com.example.saveinstancetest D/buder: onStop
 * 2021-01-29 16:33:33.050 27040-27040/com.example.saveinstancetest E/buder: onSaveInstanceState
 */

/**
 * 从本activity进入到另外一个activity时：
 * 2021-01-29 16:35:12.927 27040-27040/com.example.saveinstancetest D/buder: onResume
 * 2021-01-29 16:35:15.492 27040-27040/com.example.saveinstancetest D/buder: onPause
 * 2021-01-29 16:35:16.054 27040-27040/com.example.saveinstancetest D/buder: onStop
 * 2021-01-29 16:35:16.055 27040-27040/com.example.saveinstancetest E/buder: onSaveInstanceState
 */

/**
 * 屏幕方向切换时：
 * 2021-01-29 16:53:48.422 11050-11050/com.example.saveinstancetest D/buder: onResume
 * 2021-01-29 16:53:50.186 11050-11050/com.example.saveinstancetest D/buder: onPause
 * 2021-01-29 16:53:50.187 11050-11050/com.example.saveinstancetest D/buder: onStop
 * 2021-01-29 16:53:50.188 11050-11050/com.example.saveinstancetest E/buder: onSaveInstanceState
 * 2021-01-29 16:53:50.189 11050-11050/com.example.saveinstancetest D/buder: onDestroy
 * 2021-01-29 16:53:50.236 11050-11050/com.example.saveinstancetest D/buder: onCreate
 * 2021-01-29 16:53:50.237 11050-11050/com.example.saveinstancetest E/buder: onRestoreInstanceState方式一：恢复数据，onCreate的Restore，改变后的数据 change data
 * 2021-01-29 16:53:50.242 11050-11050/com.example.saveinstancetest D/buder: onStart
 * 2021-01-29 16:53:50.243 11050-11050/com.example.saveinstancetest E/buder: onRestoreInstanceState方式二：数据恢复 onRestore，改变后的数据 change data
 * 2021-01-29 16:53:50.245 11050-11050/com.example.saveinstancetest D/buder: onResume
 */

//************************调用 onRestoreInstanceState() ***************************************

/**
 * 屏幕方向切换时：
 * 2021-01-29 16:53:48.422 11050-11050/com.example.saveinstancetest D/buder: onResume
 * 2021-01-29 16:53:50.186 11050-11050/com.example.saveinstancetest D/buder: onPause
 * 2021-01-29 16:53:50.187 11050-11050/com.example.saveinstancetest D/buder: onStop
 * 2021-01-29 16:53:50.188 11050-11050/com.example.saveinstancetest E/buder: onSaveInstanceState
 * 2021-01-29 16:53:50.189 11050-11050/com.example.saveinstancetest D/buder: onDestroy
 * 2021-01-29 16:53:50.236 11050-11050/com.example.saveinstancetest D/buder: onCreate
 * 2021-01-29 16:53:50.237 11050-11050/com.example.saveinstancetest E/buder: onRestoreInstanceState方式一：恢复数据，onCreate的Restore，改变后的数据 change data
 * 2021-01-29 16:53:50.242 11050-11050/com.example.saveinstancetest D/buder: onStart
 * 2021-01-29 16:53:50.243 11050-11050/com.example.saveinstancetest E/buder: onRestoreInstanceState方式二：数据恢复 onRestore，改变后的数据 change data
 * 2021-01-29 16:53:50.245 11050-11050/com.example.saveinstancetest D/buder: onResume
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "buder";
    private static final String STORE_KEY = "key_one";
    TextView mTextView;
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        mTextView = findViewById(R.id.text);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        //方式一：数据恢复，从K-V中获取销毁之前的存储值
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString(STORE_KEY);
            Log.e(TAG, "onRestoreInstanceState方式一：恢复数据，onCreate的Restore，" + test);
            mTextView.setText(test);
        }
    }

    //在pause/stop之间进行界面上的数据存储，到K-V中
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
//        String change = mTextView.getText().toString();
        outState.putString(STORE_KEY, "改变后的数据 change data");
    }

    //方式二：数据恢复，从K-V中获取销毁之前的存储值
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString(STORE_KEY);
        Log.e(TAG, "onRestoreInstanceState方式二：数据恢复 onRestore，" + test);
        mTextView.setText(test);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

