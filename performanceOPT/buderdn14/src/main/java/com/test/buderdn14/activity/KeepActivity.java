package com.test.buderdn14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class KeepActivity extends Activity {
    private static final String TAG = "KeepActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "启动Keep");
        Window window = getWindow();
        //设置这个activity在左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        //宽高为1
        attributes.width = 1;
        attributes.height = 1;
        //起始位置左上角
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);

        KeepManager.getInstance().setKeepActivity(this);
    }
}
