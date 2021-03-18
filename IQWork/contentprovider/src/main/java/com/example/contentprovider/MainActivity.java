package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
}