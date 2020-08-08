package com.test.concurrency_tools_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncTask<Object, Object, Object> execute = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }
        }.execute();
    }
}
