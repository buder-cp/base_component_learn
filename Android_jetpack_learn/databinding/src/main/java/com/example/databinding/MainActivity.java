package com.example.databinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databinding.bindingadapter.BindingAdapterActivity;
import com.example.databinding.eventhandle.EventHandleActivity;
import com.example.databinding.includelayout.IncludeLayoutActivity;
import com.example.databinding.simpletextview.SimpleTextViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void simpleTextViewButtonClicked(View view) {
        Intent intent = new Intent(this, SimpleTextViewActivity.class);
        startActivity(intent);
    }

    public void EventHandleButtonClicked(View view) {
        Intent intent = new Intent(this, EventHandleActivity.class);
        startActivity(intent);
    }

    public void IncludeLayoutButtonClicked(View view) {
        Intent intent = new Intent(this, IncludeLayoutActivity.class);
        startActivity(intent);
    }

    public void BindingAdapterButtonClicked(View view) {
        Intent intent = new Intent(this, BindingAdapterActivity.class);
        startActivity(intent);
    }
//
//    public void TwoWayBindingButtonClicked(View view) {
//        Intent intent = new Intent(this, TwoWayBindingActivity.class);
//        startActivity(intent);
//    }
//
//    public void RecyclerViewButtonClicked(View view) {
//        Intent intent = new Intent(this, RecyclerViewActivity.class);
//        startActivity(intent);
//    }
}