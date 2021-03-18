package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    //数据库名称
    private static final String DATA_BASE_NAME = "people.db";

    //数据库版本号
    private static final int DATE_BASE_VERSION = 1;

    //表名-男孩
    public static final String BOY_TABLE_NAME = "boy";

    //表名-女孩
    public static final String GIRL_TABLE_NAME = "girl";

    //创建表-男孩（两列：主键自增长、姓名）
    private final String CREATE_BOY_TABLE = "create table " + BOY_TABLE_NAME + "(_id integer primary key autoincrement, name text)";

    //创建表-女孩（两列：主键自增长、姓名）
    private final String CREATE_GIRL_TABLE = "create table " + GIRL_TABLE_NAME + "(_id integer primary key autoincrement, name text)";

    public DbOpenHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATE_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOY_TABLE);//创建男孩表
        db.execSQL(CREATE_GIRL_TABLE);//创建女孩表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //一些数据库升级操作
    }
}
