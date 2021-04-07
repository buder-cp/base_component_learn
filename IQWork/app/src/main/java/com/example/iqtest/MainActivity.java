package com.example.iqtest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iqtest.bluetooth.BluetoothNewActivity;
import com.example.iqtest.signalTest.SignalTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button heapTestBtn;
    Button signalBtn;
    Button contentProviderBtn;
    Button broadcastBtn;

    private MyInstalledReceiver installedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heapTestBtn = findViewById(R.id.native_heap_test);
        signalBtn = findViewById(R.id.signal_info);
        contentProviderBtn = findViewById(R.id.contentProvider);
        broadcastBtn = findViewById(R.id.broadcast);
        heapTestBtn.setOnClickListener(this);
        signalBtn.setOnClickListener(this);
        contentProviderBtn.setOnClickListener(this);
        broadcastBtn.setOnClickListener(this);

        installedReceiver = new MyInstalledReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addAction("android.intent.action.PACKAGE_REPLACED");
        filter.addDataScheme("package");
        registerReceiver(installedReceiver, filter);

        /**
         * 长虹二期，过滤获取能通过图标启动的 系统应用、用户安装的三方应用
         */
//        AppInfoUtils.getSpecialPackages(this, findViewById(R.id.jump_btn));


        /**
         * 长虹一期应用跳转调试
         */
//        ChangHongAppJump.testChangHongApp(this, findViewById(R.id.jump_btn));

        /**
         * 长虹一期应用APP请求接口
         */
//        DataRequest.doPost();

        /**
         * 测试蓝牙
         */
//        BluetoothActivity.goToBluetoothTest(MainActivity.this, findViewById(R.id.Bluetooth_btn));
        BluetoothNewActivity.goToBluetoothNewTest(MainActivity.this, findViewById(R.id.Bluetooth_btn));

        /**
         * 长虹二期腾讯、优酷tab请求接口
         */
//        DataRequestGET.doRequest();
//        DataRequestPOST.doRequest();//OK Test

        /**
         * 电视果tv3.5
         * 测试contentProvider
         * 在按钮： R.id.contentProvider
         */
//        useProvider();

        /**
         * 电视果tv3.5
         * 测试提供broadcastReceiver
         */

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (installedReceiver != null) {
            this.unregisterReceiver(installedReceiver);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.native_heap_test) {
//            Intent intent = new Intent(MainActivity.this, NativeHeapActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.signal_info) {
//            SignalTest.getSignal(this);
//            SignalTest.getSignalIsOk(this);
//            SignalTest.switchSignal(this);
            SignalTest.getSignalNew(this);
        } else if (view.getId() == R.id.contentProvider) {
            useProvider();
        } else if (view.getId() == R.id.broadcast) {
//            Intent intent = new Intent();
//            intent.setAction("com.gala.video.app.epg.opr.setting.SettingExportedReceiver");
//            intent.putExtra("gala.dest.field", "gala.setting.netspeed");
//            sendBroadcast(intent);

//            gotoPlaySetting();
//            gotoAboutSetting();
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName("com.tvguo.app", "com.tvguo.app.SplashActivity"));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
    }

    /**
     * 测试 BroadCastReceiver
     * 跳转 播放设置页，关于页
     */
    private void gotoPlaySetting() {
        Intent intent = new Intent();
//        intent.setAction("com.gala.video.app.epg.opr.setting.SettingExportedReceiver");
        intent.setAction("com.gitv.dvb.app.epg.opr.setting.SettingExportedReceiver");
        intent.putExtra("gala.dest.field", "gala.setting.player");
        sendBroadcast(intent);
    }

    private void gotoAboutSetting() {
        Intent intent = new Intent();
//        intent.setAction("com.gala.video.app.epg.opr.setting.SettingExportedReceiver");
        intent.setAction("com.gitv.dvb.app.epg.opr.setting.SettingExportedReceiver");
        intent.putExtra("gala.dest.field", "gala.setting.about");
        sendBroadcast(intent);
    }

    /**
     * 测试contentProvider
     * 在按钮： R.id.contentProvider，
     * 需要先安装 contentProvider应用，创建数据库后测试
     */
    private void useProvider() {
        Uri boyUri = Uri.parse("content://com.example.contentprovider.MyFirstContentProvider/boy");

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "张三");
        getContentResolver().insert(boyUri, contentValues);
        //getContentResolver().delete(boyUri, )
        Cursor boyCursor = getContentResolver().query(boyUri, new String[]{"_id", "name"}, null, null, null);
        if (boyCursor != null) {
            while (boyCursor.moveToNext()) {
                Log.e("buder", "ID:" + boyCursor.getInt(boyCursor.getColumnIndex("_id")) + "  name:" + boyCursor.getString(boyCursor.getColumnIndex("name")));
            }
            boyCursor.close();
        }

        Uri girlUri = Uri.parse("content://com.example.contentprovider.MyFirstContentProvider/girl");
        contentValues.clear();
        //contentValues.put("name", "李四");
        //getContentResolver().insert(girlUri, contentValues);
        Cursor girlCursor = getContentResolver().query(girlUri, new String[]{"_id", "name"}, null, null, null);
        if (girlCursor != null) {
            while (girlCursor.moveToNext()) {
                Log.e("buder", "ID:" + girlCursor.getInt(girlCursor.getColumnIndex("_id"))
                        + "  name:" + girlCursor.getString(girlCursor.getColumnIndex("name")));
            }
            girlCursor.close();
        }
    }

    class MyInstalledReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {        // install
                String packageName = intent.getDataString();
                Log.i("homer", "安装了 :" + packageName);
            }

            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {    // uninstall
                String packageName = intent.getDataString();
                Log.i("homer", "卸载了 :" + packageName);
            }
        }
    }

}