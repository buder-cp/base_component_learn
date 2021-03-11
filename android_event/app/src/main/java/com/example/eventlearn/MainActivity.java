package com.example.eventlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 在view的 DispatchTouchEvent方法中
         *             ListenerInfo li = mListenerInfo;
         *             if (li != null && li.mOnTouchListener != null
         *                     && (mViewFlags & ENABLED_MASK) == ENABLED
         *                     && li.mOnTouchListener.onTouch(this, event)) {
         *                 result = true;
         *             }
         *
         *             if (!result && onTouchEvent(event)) {
         *                 result = true;
         *             }
         *  如果 touchlistener不为空，则先执行listener中方法，返回true则不会往下传递
         *
         */

        /**
         * onTouch --> onTouchEvent --> onClick
         * 注册了mOnTouchListener        注册了OnClickListener
         */
        findViewById(R.id.myView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        findViewById(R.id.myView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}