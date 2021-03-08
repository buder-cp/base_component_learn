package com.example.eventlearn;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ViewGroupB extends LinearLayout{

    public ViewGroupB(Context context) {
        super(context);
    }
    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ViewGroupB(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d("付勇焜----->","ViewGroupB dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d("付勇焜----->","ViewGroupB onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("付勇焜----->","ViewGroupB onTouchEvent");
        return super.onTouchEvent(event);
    }
}
