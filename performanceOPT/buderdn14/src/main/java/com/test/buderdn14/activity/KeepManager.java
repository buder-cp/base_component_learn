package com.test.buderdn14.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepManager {
    private static final KeepManager ourInstance = new KeepManager();

    public static KeepManager getInstance() {
        return ourInstance;
    }

    private KeepManager() {
    }

    private KeepReceiver keepReceiver;
    private WeakReference<Activity> mKeepActivity;

    /**
     * 注册
     *
     * @param context
     */
    public void registerKeepReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        keepReceiver = new KeepReceiver();
        context.registerReceiver(keepReceiver, filter);
    }

    /**
     * 反注册
     *
     * @param context
     */
    public void unRegisterKeepReceiver(Context context) {
        if (null != keepReceiver) {
            context.unregisterReceiver(keepReceiver);
        }
    }

    /**
     * 启动1个像素的KeepActivity
     *
     * @param context
     */
    public void startKeep(Context context) {
        Intent intent = new Intent(context, KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * finish1个像素的KeepActivity
     */
    public void finishKeep() {
        if (null != mKeepActivity) {
            Activity activity = mKeepActivity.get();
            if (null != activity) {
                activity.finish();
            }
            mKeepActivity = null;
        }
    }

    public void setKeepActivity(KeepActivity mKeepActivity) {
        this.mKeepActivity = new WeakReference<Activity>(mKeepActivity);
    }
}
