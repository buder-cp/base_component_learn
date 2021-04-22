package com.example.databinding.twowaybinding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityTwoWayBindingBinding;

/**
 * 演示双向绑定
 */
public class TwoWayBindingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTwoWayBindingBinding activityTwoWayBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding);
        activityTwoWayBindingBinding.setViewModel(new TwoWayBindingViewModel());
    }
}
