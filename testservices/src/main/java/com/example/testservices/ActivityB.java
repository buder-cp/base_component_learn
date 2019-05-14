package com.example.testservices;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

public class ActivityB extends Activity implements View.OnClickListener{

    private TestService service = null;

    private boolean isBind = false;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TestService.MyBinder myBinder = (TestService.MyBinder)binder;
            service = myBinder.getService();
            Log.i("buder", "ActivityB 执行 onServiceConnected");
            int num = service.getRandomNumber();
            Log.i("buder", "ActivityB 执行 getRandomNumber = " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i("buder", "ActivityB 执行 onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);
        findViewById(R.id.btnFinish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBindService){
            //单击了“bindService”按钮
            Intent intent = new Intent(this, TestService.class);
            intent.putExtra("from", "ActivityB");
            Log.i("buder", "----------------------------------------------------------------------");
            Log.i("buder", "ActivityB 执行 bindService");
            bindService(intent, conn, BIND_AUTO_CREATE);
        }else if(v.getId() == R.id.btnUnbindService){
            //单击了“unbindService”按钮
            if(isBind){
                Log.i("buder", "----------------------------------------------------------------------");
                Log.i("buder", "ActivityB 执行 unbindService");
                unbindService(conn);
            }
        }else if(v.getId() == R.id.btnFinish){
            //单击了“Finish”按钮
            Log.i("buder", "----------------------------------------------------------------------");
            Log.i("buder", "ActivityB 执行 finish");
            this.finish();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("buder", "ActivityB 执行 onDestroy");
    }
    
}
