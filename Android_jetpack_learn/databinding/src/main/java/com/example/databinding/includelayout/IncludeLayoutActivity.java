package com.example.databinding.includelayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.Book;
import com.example.databinding.R;
import com.example.databinding.databinding.ActivityIncludeLayoutBinding;

/**
 * 演示布局嵌套的绑定
 */
public class IncludeLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIncludeLayoutBinding activityIncludeLayoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_include_layout);
        Book book = new Book("中關村金融之星", "kuns");
//        activityIncludeLayoutBinding.setBook(book);
        activityIncludeLayoutBinding.setBook(book);
    }
}
