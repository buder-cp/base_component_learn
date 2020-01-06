package com.example.annotationlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@BindId(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindId(R.id.hello_txt)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        BindIdApi.bindId(this);

//        tv = findViewById(R.id.hello_txt);
        tv.setText("通过注解ID查找");

//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        BindOnClickApi.bindOnClick(this);
    }

    @BindOnClick(R.id.hello_txt)
    private void txtClick(View view) {
        Toast.makeText(MainActivity.this, "annotation", Toast.LENGTH_SHORT).show();
    }
}
