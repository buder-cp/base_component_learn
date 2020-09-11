package com.example.appusagestate;


import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Process;
import android.util.Log;

import java.util.List;

@SuppressLint("NewApi")
public class UsageStatsManagerUtils {

    private static final String PKG_USAGE_SETTING_ACTION = "android.settings.USAGE_ACCESS_SETTINGS";

    private static UsageStatsManager sUsageManager = null;
    private static AppOpsManager sOpsManager = null;

    private static UsageStatsManager getUsageManager(Context context) {
        if (sUsageManager == null) {
            synchronized (UsageStatsManagerUtils.class) {
                if (sUsageManager == null) {
                    Context ctx = context;
                    if(ctx != null) {
                        sUsageManager = (UsageStatsManager) ctx.getSystemService(Context.USAGE_STATS_SERVICE);
                    }
                }
            }
        }
        return sUsageManager;
    }

    private static AppOpsManager getAppOpsManager(Context context) {
        if (sOpsManager == null) {
            synchronized (UsageStatsManagerUtils.class) {
                if (sOpsManager == null) {
                    Context ctx = context;
                    if(ctx != null) {
                        sOpsManager = (AppOpsManager) ctx.getSystemService(Context.APP_OPS_SERVICE);
                    }
                }
            }
        }
        return sOpsManager;
    }

    public static boolean isSupportUsageStats(Context ctx) {
        if (null == ctx || Build.VERSION.SDK_INT < 21) {
            return false;
        }

        Intent it  = new Intent(PKG_USAGE_SETTING_ACTION);
        int nCount = 0;

        try {
            PackageManager packageManager = ctx.getPackageManager();
            List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(it,
                    PackageManager.MATCH_DEFAULT_ONLY);
            nCount = resolveInfo.size();
        } catch (Exception e) {

        }

        return (nCount > 0);
    }

    public static boolean isGrantPermission(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }

        AppOpsManager opsManager = getAppOpsManager(context);

        Context ctx = context;
        if(ctx != null) {
            return (opsManager.checkOp(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    Process.myUid(), ctx.getPackageName()) == AppOpsManager.MODE_ALLOWED);
        } else {
            return false;
        }
    }

    public static ComponentName getMoveToFgComponent(Context context, long beginTime, long endTime) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }

        UsageStatsManager usageManager = getUsageManager(context);
        UsageEvents events = usageManager.queryEvents(beginTime, endTime);
        String pkgName = null;
        String clsName = null;
        while (events.hasNextEvent()) {
            UsageEvents.Event e = new UsageEvents.Event();
            events.getNextEvent(e);
            if (e.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                pkgName = e.getPackageName();
                clsName = e.getClassName();
                Log.e("buder", "pkgName:" + pkgName + " clsName:" + clsName);
            }
        }

        ComponentName comp = null;
        if (pkgName != null && clsName != null) {
            comp = new ComponentName(pkgName, clsName);
        }

        return comp;
    }
}
