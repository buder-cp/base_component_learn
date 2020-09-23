package com.example.iqtest.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppInfoUtils {

    private static final String TAG = "AppInfoUtils";

    public static List<PackageInfo> getSpecialPackages(final Context context, View view) {

        final List<PackageInfo> pms = context.getPackageManager().getInstalledPackages(0);
        final PackageManager pm = context.getApplicationContext().getPackageManager();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, pms.size() + "");//原始一共安装的应用数目
                Collections.sort(pms, new Comparator<PackageInfo>() {//所有应用按照安装时间排序
                    @Override
                    public int compare(PackageInfo o1, PackageInfo o2) {
                        long date1 = o1.firstInstallTime;
                        long date2 = o2.firstInstallTime;
                        if (date1 < date2) {
                            return 1;
                        }
                        return -1;
                    }
                });

                for (PackageInfo packageInfo : pms) {
                    //排除掉不能通过图标启动的应用
                    boolean notActiveApp = notActiveApp(context, packageInfo.packageName);
                    if (notActiveApp) {
                        continue;
                    }

                    String packageName = packageInfo.applicationInfo.packageName;//获取包名
                    String appName = packageInfo.applicationInfo.loadLabel(pm).toString();//获取应用名称
                    Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);//获取图标

                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                        //输出用户自己安装的三方应用
                        Log.e(TAG, appName + " " + packageName);
                    } else {
                        //输出预转的内置应用
                        Log.d(TAG, appName + " " + packageName);
                    }
                }

            }
        });
        return pms;
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
