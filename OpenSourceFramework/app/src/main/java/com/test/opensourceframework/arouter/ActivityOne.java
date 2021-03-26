package com.test.opensourceframework.arouter;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.opensourceframework.MainActivity;
import com.test.opensourceframework.R;

public class ActivityOne extends AppCompatActivity {

    public static final String TAG = "buder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * 测试单一路由，降级监听，例如单一路由失败的话进行后续处理
     */
    private void ARouterCallBack() {
        ARouter.getInstance()
                .build("/com/hq")
                .navigation(this, new NavCallback() {

                    @Override
                    public void onFound(Postcard postcard) {
                        Log.e(TAG, "onArrival: 找到了 ");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.e(TAG, "onArrival: 找不到了 ");
                        Toast.makeText(ActivityOne.this, "未找到目标页面 path=" + postcard.getPath() + " group=" + postcard.getGroup() +
                                " 做降级处理，5s后跳转降级页", Toast.LENGTH_LONG).show();
                        jumpDegradePage(ActivityOne.this, postcard);
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e(TAG, "onArrival: 跳转完了 ");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.e(TAG, "onArrival: 被拦截了 ");
                    }
                });
    }

    private void jumpDegradePage(ActivityOne activityOne, Postcard postcard) {
        startActivity(new Intent());
    }
}