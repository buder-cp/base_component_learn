package com.example.iqtest.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iqtest.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothNewActivity extends AppCompatActivity {

    private static final String TAG = "BluetoothNewActivity";

    public static void goToBluetoothNewTest(final Context context, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BluetoothNewActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "BluetoothActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        registerBroadcastReveiver();
        findViewById(R.id.blue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取已经配对的蓝牙信息
//                getDevices();

                //方式二：使用系统API反射，没有问题
//                checkLinkState1();

            }
        });

    }

    private void checkLinkState1() {
        ArrayList<BluetoothDevice> deviceList = new ArrayList<>();
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Class<BluetoothAdapter> bluetoothAdapterClass = BluetoothAdapter.class;//得到BluetoothAdapter的Class对象
        try {
            //得到连接状态的方法
            Method method = bluetoothAdapterClass.getDeclaredMethod("getConnectionState", (Class[]) null);
            //打开权限
            method.setAccessible(true);
            int state = (int) method.invoke(adapter, (Object[]) null);
            if (state == BluetoothAdapter.STATE_CONNECTED) {
                Log.i(TAG, "BluetoothAdapter.STATE_CONNECTED");
                Set<BluetoothDevice> devices = adapter.getBondedDevices();
                Log.i(TAG, "devices:" + devices.size());

                for (BluetoothDevice device : devices) {
                    Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                    method.setAccessible(true);
                    boolean isConnected = (boolean) isConnectedMethod.invoke(device, (Object[]) null);
                    if (isConnected) {
                        Log.i(TAG, "connected:" + device.getName());
                        deviceList.add(device);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDevices() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            Log.i(TAG, "name is : " + bluetoothDevice.getName() + " state is:" + bluetoothDevice.getBondState());
        }
        Log.i(TAG, "size:" + bondedDevices.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBroadcastReceiver();
    }

    private void registerBroadcastReveiver() {
//        IntentFilter connectStateChangeFilter = new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
//        IntentFilter stateChangeFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter connectFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        IntentFilter disConnectFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

//        registerReceiver(stateChangeReceiver, connectStateChangeFilter);
//        registerReceiver(stateChangeReceiver, stateChangeFilter);
        registerReceiver(stateChangeReceiver, connectFilter);
        registerReceiver(stateChangeReceiver, disConnectFilter);


        /**
         * Android 9.0以上已经没有BluetoothInputDevice、BluetoothAvrcpController
         * 均使用 BluetoothDevice 即可
         *
         */
//        IntentFilter disConnectFilter = new IntentFilter(BluetoothInputDevice.ACTION_CONNECTION_STATE_CHANGED);
//        IntentFilter headsetFilter = new IntentFilter(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
//        IntentFilter a2dpFilter = new IntentFilter(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
//        IntentFilter disConnectFilter = new IntentFilter(BluetoothAvrcpController.ACTION_CONNECTION_STATE_CHANGED);
//
//        registerReceiver(stateChangeReceiver, headsetFilter);
//        registerReceiver(stateChangeReceiver, a2dpFilter);

    }

    private void unRegisterBroadcastReceiver() {
        unregisterReceiver(stateChangeReceiver);
    }

    private BroadcastReceiver stateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent connIntent) {
            String action = connIntent.getAction();
            Log.i(TAG, "bluetooth, onReceive, action: " + action);

            /**
             * device字段中.getName() 即可获取到断开连接的设备名称
             */
            BluetoothDevice device = connIntent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            int prevState = connIntent.getIntExtra(BluetoothProfile.EXTRA_PREVIOUS_STATE, -1);
            int state = connIntent.getIntExtra(BluetoothProfile.EXTRA_STATE, -1);
            Log.e(TAG, "deviceName:" + device.getName() + "  ,prevState:" + prevState + "  ,state:" + state);

            if (BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
                Log.e(TAG, "ACTION_CONNECTION_STATE_CHANGED");
            }

            if (BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
                Log.e(TAG, "BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED");
            }

            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Log.e(TAG, "ACTION_ACL_CONNECTED");
            }
            if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Log.e(TAG, "ACTION_ACL_DISCONNECTED");
            }

            if (BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
                Log.e(TAG, "ACTION_CONNECTION_STATE_CHANGED");
            }

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                Log.e(TAG, "ACTION_STATE_CHANGED");
            }
        }
    };
}