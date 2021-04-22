package com.example.databinding.bindingadapter;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityBindingAdapterBinding;

/**
 * 演示@BindingAdapter的使用
 */
public class BindingAdapterActivity extends AppCompatActivity {
    private ActivityBindingAdapterBinding activityBindingAdapterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBindingAdapterBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding_adapter);

        activityBindingAdapterBinding.setNetworkImage("https://img1.doubanio.com/view/subject/l/public/s29670267.jpg");
        activityBindingAdapterBinding.setLocalImage(R.mipmap.ic_launcher);
        activityBindingAdapterBinding.setImagePadding(40);
        activityBindingAdapterBinding.setClickHandler(new ClickHandler());
    }

    public class ClickHandler {
        public void onClick(View view) {
            activityBindingAdapterBinding.setImagePadding(180);
        }
    }
}
