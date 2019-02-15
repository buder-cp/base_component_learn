package com.example.gsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transformStepOne();
//                transformStepTwo();
                transformStepThree();

                //复杂数据结构解析示例
                transform_completed();
            }
        });
    }

    /**
     * 第一种为JsonObject
     */
    private void transformStepOne() {
        String json = "{'name':'buder','age':'20'}";
        // 将json编译成对象
        Gson gson = new Gson();
        Person person = gson.fromJson(json, Person.class);
        Log.e("Person Object", person.getName() + " " + person.getAge());
    }

    /**
     * 第二种为JsonObject嵌套JsonObject，需要改下Person数据结构，将List改为Object
     */
//    private void transformStepTwo() {
//        String json = "{'name':'buder','age':'20', 'address':{'postid':'473000'}}";
//
//        Gson gson = new Gson();
//        Person person = gson.fromJson(json, Person.class);
//        Log.e("Person Object", person.getAddress().getPostid());
//    }

    /**
     * 第三种为JsonObject嵌套JSonArray
     */
    private void transformStepThree() {
        String json = "{'name':'buder','age':'20', 'address':[{'postid':'100011'}, {'postid':'666999'}]}";

        Gson gson = new Gson();
        Person person = gson.fromJson(json, Person.class);
        Log.e("Person Object", person.getAddress().get(1).getPostid());
    }

    private void transform_completed() {
        String json = "{'org':{'orgId':'orgId','orgName':'orgName'},'biz': [{'appcode':5588,'subscode':'subscode0'},{'appcode':6699,'subscode':'subscode1'}]}";
        Gson gson = new Gson();
        ComOpen m3 = gson.fromJson(json, ComOpen.class);
        Log.e("2", "orgId：" + m3.getOrg().getOrgId() + "  " + "appcode：" + m3.getBiz().get(0).getAppcode());
    }

}