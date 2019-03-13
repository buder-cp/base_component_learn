package com.example.buildermodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person.Builder builder = new Person.Builder();

        Person person = builder.age(12)
                .name("buder")
                .height(181)
                .weight(80)
                .build();

        Log.e("buder", "name: " + person.getName() + ", age: "
                + person.getAge() + ", height: " + person.getHeight() + ", weight: " + person.getWeight());

    }
}
