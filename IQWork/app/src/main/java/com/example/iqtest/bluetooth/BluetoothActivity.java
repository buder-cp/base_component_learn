package com.example.iqtest.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
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

public class BluetoothActivity extends AppCompatActivity {

    private static final String TAG = "BluetoothActivity";

    public static void goToBluetoothTest(final Context context, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BluetoothActivity.class);
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
                getDevices();

                //方式一：不用反射，个别设备无法检测到
                checkLinkState();

                //方式二：使用系统API反射，没有问题
                checkLinkState1();//OK test All

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

    private void checkLinkState() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        int flag = -1;

        int a2dp = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP);
        int headset = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET);
        int health = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH);
        int health1 = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.GATT);
        int health2 = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.GATT_SERVER);
        int health3 = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET);
        int health4 = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEARING_AID);
        int health5 = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HID_DEVICE);
        int health6 = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.SAP);

        if (a2dp == BluetoothProfile.STATE_CONNECTED) {
            flag = a2dp;
        } else if (headset == BluetoothProfile.STATE_CONNECTED) {
            flag = headset;
        } else if (health == BluetoothProfile.STATE_CONNECTED) {
            flag = health;
        } else if (health1 == BluetoothProfile.STATE_CONNECTED) {
            flag = health1;
        } else if (health2 == BluetoothProfile.STATE_CONNECTED) {
            flag = health2;
        } else if (health3 == BluetoothProfile.STATE_CONNECTED) {
            flag = health3;
        } else if (health4 == BluetoothProfile.STATE_CONNECTED) {
            flag = health4;
        } else if (health5 == BluetoothProfile.STATE_CONNECTED) {
            flag = health5;
        } else if (health6 == BluetoothProfile.STATE_CONNECTED) {
            flag = health6;
        }
        Log.e(TAG, "flag is " + flag);

        if (flag != -1) {
            bluetoothAdapter.getProfileProxy(this, new BluetoothProfile.ServiceListener() {

                @Override
                public void onServiceDisconnected(int profile) {
                    // TODO Auto-generated method stub
                    Log.i(TAG, "onServiceDisconnected");

                }

                @Override
                public void onServiceConnected(int profile, BluetoothProfile proxy) {
                    // TODO Auto-generated method stub
                    List<BluetoothDevice> mDevices = proxy.getConnectedDevices();
                    if (mDevices != null && mDevices.size() > 0) {
                        for (BluetoothDevice device : mDevices) {
                            Log.i(TAG, "device name: " + device.getName());
                        }
                    } else {
                        Log.i(TAG, "mDevices is null");
                    }
                }
            }, flag);
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
        IntentFilter connectStateChangeFilter = new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        IntentFilter stateChangeFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter connectFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        IntentFilter disConnectFilter = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        registerReceiver(stateChangeReceiver, connectStateChangeFilter);
        registerReceiver(stateChangeReceiver, stateChangeFilter);
        registerReceiver(stateChangeReceiver, connectFilter);
        registerReceiver(stateChangeReceiver, disConnectFilter);
    }

    private void unRegisterBroadcastReceiver() {
        unregisterReceiver(stateChangeReceiver);
    }

    private BroadcastReceiver stateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            Log.i(TAG, "bluetooth, stateChangeReceiver, action: " + action);

            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Log.e(TAG, "connect");
            }
            if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Log.e(TAG, "disconnect");
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