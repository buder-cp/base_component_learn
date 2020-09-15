package com.example.appuseage2;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Project Name:GetTopActivity
 * Class description:
 * Create User:wxb
 * Create Time:2016/4/23 20:47
 */
public class TestService extends Service {
    public static final String TAG = "TestService";
    private MyThread myThread = null;

    private static class MyThread extends Thread {
        private Context context;
        private boolean isRun = true;

        private MyThread(Context context) {
            this.context = context;
        }

        public void setStop() {
            isRun = false;
        }

        @Override
        public void run() {
            while (isRun) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    getTopApp(context);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void getTopApp(Context context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                UsageStatsManager m = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
                if (m != null) {
                    long now = System.currentTimeMillis();
                    //获取60秒之内的应用数据
                    List<UsageStats> stats = m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, now - 60 * 1000, now);
                    Log.i(TAG, "Running app number in last 60 seconds : " + stats.size());

                    String topActivity = "";

                    //取得最近运行的一个app，即当前运行的app
                    if ((stats != null) && (!stats.isEmpty())) {
                        int j = 0;
                        for (int i = 0; i < stats.size(); i++) {
                            if (stats.get(i).getLastTimeUsed() > stats.get(j).getLastTimeUsed()) {
                                j = i;
                            }
                        }
                        topActivity = stats.get(j).getPackageName();
                    }
                    Log.i(TAG, "top running app is : " + topActivity);
                }
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myThread = new MyThread(this);
        myThread.start();
        Log.i(TAG, "Service is start.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myThread.setStop();
        Log.i(TAG, "Service is stop.");
    }
}
