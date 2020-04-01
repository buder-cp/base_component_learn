package com.test.urijump;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
//        if (null != intent) {
//            Uri uri = intent.getData();
//            if (uri == null) {
//                return;
//            }
//            String actionData = uri.getQueryParameter("action");
//            String secretData = uri.getQueryParameter("secret");
//
//            TextView tv = findViewById(R.id.txt);
//            tv.append("\n传过来的action值为:" + actionData);
//            tv.append("\n传过来的secret值为:" + secretData);
//        }

        if (null != intent) {
            Bundle extras = intent.getExtras();
            String value1 = extras.getString("key1");
            String value2 = extras.getString("key2");

            TextView tv = findViewById(R.id.txt);
            tv.append("\n传过来的key1值为:" + value1);
            tv.append("\n传过来的key2值为:" + value2);
        }

    }
}
