package com.example.appusagestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private static final String PACKAGE_NAME_UNKNOWN = "unknown";
    private static final String TAG = "buder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0, 0);
//        findViewById(R.id.activity_start_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                getTopActivityPackageName(getApplicationContext());
////
////                Log.e(TAG, getForegroundApp() + " ");
//
////                Intent intent = new Intent();
////                intent.setAction("qwe.666");
////                startActivity(intent);
//
////                boolean grantPermission = UsageStatsManagerUtils.isGrantPermission(getApplicationContext());
////                Log.i("buder", "is grantPermission :" + grantPermission);
////
////                boolean supportUsageStats = UsageStatsManagerUtils.isSupportUsageStats(getApplicationContext());
////
//////                if ()
////
////                long end = System.currentTimeMillis();
////                long begin = end - 60 * 60000L;
////                ComponentName moveToFgComponent = UsageStatsManagerUtils.getMoveToFgComponent(getApplicationContext(), begin, end);
////                Log.i("buder", "now show :" + "getPackageName is :" + moveToFgComponent.getPackageName() + "className is :" + moveToFgComponent.getClassName());
////                gotoAboutSetting();
//
////                Intent intent = new Intent("com.gala.video.app.setting.AboutActivity");
////                startActivity(intent);
//
//            }
//        });


        findViewById(R.id.broad_cast_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction("com.gitv.dvb.app.epg.opr.setting.SettingExportedReceiver");
                intent.putExtra("gala.dest.field", "gala.setting.about");
                sendBroadcast(intent);

            }
        });
    }

    private void gotoAboutSetting() {
        Intent intent = new Intent();
//        intent.setAction("com.gala.video.app.epg.opr.setting.SettingExportedReceiver");
        intent.setAction("com.gitv.dvb.app.epg.opr.setting.SettingExportedReceiver");
        intent.putExtra("gala.dest.field", "gala.setting.about");
        sendBroadcast(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume()");
        super.onResume();
    }

//    private String getForegroundApp() {
//        long ts = System.currentTimeMillis();
//        final UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
//        List<UsageStats> queryUsageStats =
//                usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, ts - 2000, ts);
//        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
//            return null;
//        }
//        UsageStats recentStats = null;
//        for (UsageStats usageStats : queryUsageStats) {
//            if (recentStats == null || recentStats.getLastTimeUsed() < usageStats.getLastTimeUsed()) {
//                recentStats = usageStats;
//            }
//        }
//        return recentStats.getPackageName();
//    }


//    public static String getTopActivityPackageName(@NonNull Context context) {
//
//        final UsageStatsManager usageStatsManager2 = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);// Context.USAGE_STATS_SERVICE);
//        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        final List<UsageStats> queryUsageStats = usageStatsManager2.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, currentYear - 2, currentYear);
//        Log.e("buder", queryUsageStats.size() + " ");
//
//
//        final UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
//        if (usageStatsManager == null) {
//            return PACKAGE_NAME_UNKNOWN;
//        }
//
//        String topActivityPackageName = PACKAGE_NAME_UNKNOWN;
//        long time = System.currentTimeMillis();
//        // 查询最后十秒钟使用应用统计数据
//        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time - 1000 * 10 * 1000, time);
//        Log.e("buder2", usageStatsList.size() + " ");
//        // 以最后使用时间为标准进行排序
//        if (usageStatsList != null) {
//            SortedMap<Long, UsageStats> sortedMap = new TreeMap<Long, UsageStats>();
//            for (UsageStats usageStats : usageStatsList) {
//                sortedMap.put(usageStats.getLastTimeUsed(), usageStats);
//            }
//            if (sortedMap.size() != 0) {
//                topActivityPackageName = sortedMap.get(sortedMap.lastKey()).getPackageName();
//                Log.d(TAG, "Top activity package name = " + topActivityPackageName);
//            }
//        }
//
//        return topActivityPackageName;
//    }
}
