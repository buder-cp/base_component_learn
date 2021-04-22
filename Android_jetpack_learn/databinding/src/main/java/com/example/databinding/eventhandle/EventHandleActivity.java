package com.example.databinding.eventhandle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityEventHandleBinding;

public class EventHandleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventHandleBinding activityEventHandleBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_handle);
        activityEventHandleBinding.setEventHandler(new EventHandleListener(this));
    }

    /**
     * 这里以内部类的形式放置这个类，将其独立到外面也是可以的。
     */
    public static class EventHandleListener {
        private final Context context;

        public EventHandleListener(Context context) {
            this.context = context;
        }

        public void onButtonClickedss(View view) {
            Toast.makeText(context, "I am clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
