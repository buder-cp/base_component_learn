package com.example.annotationlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annotationlearn.dynamic_runtime_annotation.BindId;
import com.example.annotationlearn.dynamic_runtime_annotation.BindIdApi;
import com.example.annotationlearn.dynamic_runtime_annotation.BindOnClick;
import com.example.annotationlearn.dynamic_runtime_annotation.BindOnClickApi;
import com.example.module_annotation.BindView;
import com.example.module_library.BindViewTools;

//动态运行时注解
@BindId(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    /**
     * 静态编译时时注解
     */
    @BindView(R.id.static_annotation_txt)
    TextView mStaticTv;

    //动态运行时注解
    @BindId(R.id.dynamic_annotation_txt)
    private TextView mDynamicTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态运行时主机注册
        BindIdApi.bindId(this);

        //静态编译时时注解注册
        BindViewTools.bind(this);
        mStaticTv.setText("静态编译时注解生效");

        //动态运行时注解，onClick 注解
        mStaticTv = findViewById(R.id.dynamic_annotation_txt);
        mDynamicTv.setText("通过注解ID，运行时注解查找");

        mStaticTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        BindOnClickApi.bindOnClick(this);
    }

    //动态运行时注解
    @BindOnClick(R.id.dynamic_annotation_txt)
    private void txtClick(View view) {
        Toast.makeText(MainActivity.this, "annotation", Toast.LENGTH_SHORT).show();
    }
}
