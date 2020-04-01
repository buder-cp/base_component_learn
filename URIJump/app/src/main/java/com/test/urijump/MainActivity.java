package com.test.urijump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 方式一：类名跳转
                 */
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);


                /**
                 * 方式二：action跳转，bundle传值
                 */

                Intent intent1 = new Intent("self_define_action");
                Bundle bundle = new Bundle();
                bundle.putString("key1", "value111");
                bundle.putString("key2", "value222");
                intent1.putExtras(bundle);
                startActivity(intent1);


                /**
                 * 方式三：URI跳转，query传值
                 */
//                String uriString = "hello://test.uri.activity?action=123&secret=buder";
//                Uri uri = Uri.parse(uriString);
//                Intent intent = new Intent("self_define_uri");
//                //Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(uri);
//                startActivity(intent);


            }
        });

    }
}
