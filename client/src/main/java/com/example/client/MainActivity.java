package com.example.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.server.IRomteAidlInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BIND_SERVICE_ACTION = "android.intent.action.RESPOND_AIDL_MESSAGE";

    private IRomteAidlInterface iRomteAidlInterface;

    private Button mConnectButton;
    private Button mAcquireButton;

    private String mUsername;
    private String mUserage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mConnectButton = findViewById(R.id.connect);
        mAcquireButton = findViewById(R.id.acquire_info);

        mConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService();
            }
        });

        mAcquireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != iRomteAidlInterface) {
                    try {
                        mUsername = iRomteAidlInterface.getPersonUserName();
                        mUserage = iRomteAidlInterface.getPersonUserAge();
                        Toast.makeText(getApplicationContext(), "mUsername = " + mUsername + ",mUserage = " + mUserage, Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iRomteAidlInterface = IRomteAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iRomteAidlInterface = null;
        }
    };

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction(BIND_SERVICE_ACTION);
        final Intent newIntent = new Intent(achieveExplicitFromImplicitIntent(this, intent));
        bindService(newIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private Intent achieveExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        Intent explicitIntent = new Intent(implicitIntent);

        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    private void unBindService() {
        unbindService(serviceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }
}
