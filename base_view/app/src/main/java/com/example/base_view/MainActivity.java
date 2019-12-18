package com.example.base_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.lib_easy_use.EasyUseActivity;
import com.example.lib_multi_ly.MultiLayoutActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnEasyUse;
    private Button mBtnMultiLy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnEasyUse = findViewById(R.id.easy_use_btn);
        mBtnEasyUse.setOnClickListener(this);

        mBtnMultiLy = findViewById(R.id.multi_ly_btn);
        mBtnMultiLy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.easy_use_btn:
                Intent intent = new Intent(this, EasyUseActivity.class);
                startActivity(intent);
                break;
            case R.id.multi_ly_btn:
                Intent intent2 = new Intent(this, MultiLayoutActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }
}
