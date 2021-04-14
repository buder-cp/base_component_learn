package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestBtn = findViewById(R.id.test_content);
        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useProvider();
                getTvGuoSystemInfo(); //失敗調用
//                getData();
            }
        });
    }

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

//    public TvGuoSystemInfo getTvGuoSystemInfo() {
//        String TAG = "buder_tvguo";
//
//        String deviceName = null;
//        String firmwareVersion = null;
//        String deskTopVersion = "23423";
//        String devicesModel = null;
//        String deviceSN = null;
//
//        Context context = this;
//        Cursor cursor = null;
//        try {
//            cursor = context.getContentResolver().query(Uri.parse("content://com.tvguo.provider/settings"), null, null, null, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    deviceName = cursor.getString(cursor.getColumnIndex("device_default_name"));
//                    firmwareVersion = cursor.getString(cursor.getColumnIndex("firmware_version"));
//                    devicesModel = cursor.getString(cursor.getColumnIndex("hardware_id"));
//                    deviceSN = cursor.getString(cursor.getColumnIndex("tvguo_device_id"));
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "getTvGuoSystemInfo: " + e.toString());
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        Log.d(TAG, "get info from ContentProvider= " + "deviceName："+ deviceName+ ",firmwareVersion：" +firmwareVersion+
//                ",deskTopVersion："+ deskTopVersion+ ",devicesModel："+ devicesModel+ ",deviceSN："+ deviceSN);
//        return new TvGuoSystemInfo(deviceName, firmwareVersion, deskTopVersion, devicesModel, deviceSN);
//    }

    String deviceName = null;
    String firmwareVersion = null;
    String deskTopVersion = "6666666";
    String devicesModel = null;
    String deviceSN = null;
    String wifiMac = null;
    Context context = this;

    String TAG = "hehehe";


    public TvGuoSystemInfo getTvGuoSystemInfo() {
        try {
            Uri uri = Uri.parse("content://com.tvguo.provider/settings");
            deviceName = getDeviceName(uri);
            firmwareVersion = getString(uri, "firmware_version");
            deviceSN = getString(uri, "tvguo_device_id");
            wifiMac = getString(uri, "wifi_mac");
            devicesModel = getString(uri, "hardware_id");
        } catch (Exception e) {
            Log.d(TAG, "exception : " + e);
        }

        Log.d(TAG, "get info from ContentProvider= " + "deviceName："+ deviceName+ ",firmwareVersion：" +firmwareVersion+
                ",deskTopVersion："+ deskTopVersion+ ",devicesModel："+ devicesModel+ ",deviceSN："+ deviceSN + ", wifiMac:" + wifiMac);
        return new TvGuoSystemInfo(deviceName, firmwareVersion, deskTopVersion, devicesModel, deviceSN);
    }

    private ContentProviderClient acquireContentProviderClient(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }
        return context.getContentResolver().acquireContentProviderClient(uri);
    }

    public String getDeviceName(Uri uri) {
        String name = getString(uri, "device_name");
        return TextUtils.isEmpty(name) ? getString(uri,"device_default_name") : name;
    }

    private String getString(Uri mUri, String name) {
        String ret = "";
        Cursor cursor = null;
        try {
            cursor = acquireContentProviderClient(mUri).query(mUri, new String[]{"name", "value"},  "name='" + name + "'", null, null);
            if (cursor.moveToNext()) {
                ret = cursor.getString(1);
                if ("null".equals(ret)) {
                    ret = null;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return ret;
    }


}