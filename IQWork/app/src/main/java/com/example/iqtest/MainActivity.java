package com.example.iqtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iqtest.app.AppInfoUtils;
import com.example.iqtest.bluetooth.BluetoothActivity;
import com.example.iqtest.bluetooth.BluetoothNewActivity;
import com.example.iqtest.erqi.DataRequestPOST;
import com.example.iqtest.nativeHeap.NativeHeapActivity;
import com.example.iqtest.signalTest.SignalTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button heapTestBtn;
    Button signalBtn;

    private MyInstalledReceiver installedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heapTestBtn = findViewById(R.id.native_heap_test);
        signalBtn = findViewById(R.id.signal_info);
        heapTestBtn.setOnClickListener(this);
        signalBtn.setOnClickListener(this);

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
        ChangHongAppJump.testChangHongApp(this, findViewById(R.id.jump_btn));

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(installedReceiver != null) {
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
        }
    }

    class MyInstalledReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {		// install
                String packageName = intent.getDataString();
                Log.i("homer", "安装了 :" + packageName);
            }

            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {	// uninstall
                String packageName = intent.getDataString();
                Log.i("homer", "卸载了 :" + packageName);
            }
        }
    }

}