package com.example.iqtest.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppInfoUtils {

    private static final String TAG = "AppInfoUtils";

    public static /*List<PackageInfo>*/void  getSpecialPackages(final Context context, View view) {

//        final List<PackageInfo> pms = context.getPackageManager().getInstalledPackages(0);
//        final PackageManager pm = context.getApplicationContext().getPackageManager();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, pms.size() + "");//原始一共安装的应用数目

                //全部应用 20个验证OK
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> appInfo = context.getPackageManager().queryIntentActivities(mainIntent, -1);
                for (ResolveInfo apps : appInfo) {
                    Log.d(TAG, apps.activityInfo.packageName);
                }

                //最近使用应用
                ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RecentTaskInfo> tasks = activityManager.getRecentTasks(8, -1);
                for (int i = 0; i < tasks.size(); i++) {
                    String packageName = tasks.get(i).baseIntent.getPackage();
                    Log.e(TAG, packageName);

                    PackageInfo info = null;
                    PackageManager pm = context.getPackageManager();
                    try {
                        info = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
                        String s = info.applicationInfo.loadLabel(pm).toString();
                        Log.d(TAG, s);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                }


//                Collections.sort(pms, new Comparator<PackageInfo>() {//所有应用按照安装时间排序
//                    @Override
//                    public int compare(PackageInfo o1, PackageInfo o2) {
//                        long date1 = o1.firstInstallTime;
//                        long date2 = o2.firstInstallTime;
//                        if (date1 < date2) {
//                            return 1;
//                        }
//                        return -1;
//                    }
//                });
//
//                for (PackageInfo packageInfo : pms) {
//                    //排除掉不能通过图标启动的应用
//                    boolean notActiveApp = notActiveApp(context, packageInfo.packageName);
//                    if (notActiveApp) {
//                        continue;
//                    }
//
//                    String packageName = packageInfo.applicationInfo.packageName;//获取包名
//                    String appName = packageInfo.applicationInfo.loadLabel(pm).toString();//获取应用名称
//                    Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);//获取图标
//
//                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
//                        //输出用户自己安装的三方应用
//                        Log.e(TAG, appName + " " + packageName);
//                    } else {
//                        //输出预转的内置应用
//                        Log.d(TAG, appName + " " + packageName);
//                    }
//                }

            }
        });
//        return pms;
    }

    /**
     * 判断app能不能主动启动 否就隐藏
     */
    public static boolean notActiveApp(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null)
            return true;
        return false;
    }

}
