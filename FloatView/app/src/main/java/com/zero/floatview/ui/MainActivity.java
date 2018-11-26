package com.zero.floatview.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.zero.floatview.FloatViewControl;
import com.zero.floatview.R;

import java.io.File;

/**
 * @author LinZewu
 * @time 16-3-31
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //此方法为微信插件，可以在微信中自动安装应用程序
        //handleApkIntent();

    }

    private void handleApkIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            if (uri != null) {
                String path = uri.getPath();
                if (!TextUtils.isEmpty(path)) {
                    tryUpdate(path);
                }
            }
        }
    }

    private void tryUpdate(String path) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {

        }

    }

    private void initView() {
        findViewById(R.id.button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatViewControl.getInstance().startAnimation(MainActivity.this);
            }
        });
        findViewById(R.id.button_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatViewControl.getInstance().stopAnimation(MainActivity.this);
            }
        });

        EditText editText = (EditText)findViewById(R.id.edit_text);
        String s = editText.getText().toString();


    }


}
