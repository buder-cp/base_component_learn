package com.example.testservices;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TestService service = null;

    private boolean isBind = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TestService.MyBinder myBinder = (TestService.MyBinder) binder;
            service = myBinder.getService();
            Log.i("buder", "ActivityA 执行 onServiceConnected");
            int num = service.getRandomNumber();
            Log.i("buder", "ActivityA 执行 getRandomNumber = " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i("buder", "ActivityA 执行 onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("buder", "ActivityA 执行 onCreate");

        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);
        findViewById(R.id.btnStartActivityB).setOnClickListener(this);
        findViewById(R.id.btnFinish).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBindService) {
            //单击了“bindService”按钮
            Intent intent = new Intent(this, TestService.class);
            intent.putExtra("from", "ActivityA");
            Log.i("buder", "ActivityA 执行 bindService");
            bindService(intent, connection, BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.btnUnbindService) {
            //单击了“unbindService”按钮
            if (isBind) {
                Log.i("buder", "ActivityA 执行 unbindService");
                unbindService(connection);
            }
        } else if (v.getId() == R.id.btnStartActivityB) {
            //单击了“start ActivityB”按钮
            Intent intent = new Intent(this, ActivityB.class);
            Log.i("buder", "ActivityA 启动 ActivityB");
            startActivity(intent);
        } else if (v.getId() == R.id.btnFinish) {
            //单击了“Finish”按钮
            Log.i("buder", "ActivityA 执行 finish");
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("buder", "ActivityA 执行 onDestroy");
    }
}
