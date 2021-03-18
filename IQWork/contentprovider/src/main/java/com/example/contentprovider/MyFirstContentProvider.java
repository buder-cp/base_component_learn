package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyFirstContentProvider extends ContentProvider {

    private Context context;

    private SQLiteDatabase sqLiteDatabase;

    public static final String AUTHORITY = "com.example.contentprovider.MyFirstContentProvider";

    public static final int BOY_URI_CODE = 0;

    public static final int GIRL_URI_CODE = 1;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, DbOpenHelper.BOY_TABLE_NAME, BOY_URI_CODE);
        uriMatcher.addURI(AUTHORITY, DbOpenHelper.GIRL_TABLE_NAME, GIRL_URI_CODE);
    }

    /**
     * 获取表名
     * @param uri
     * @return
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (uriMatcher.match(uri)) {
            case BOY_URI_CODE:
                tableName = DbOpenHelper.BOY_TABLE_NAME;
                break;
            case GIRL_URI_CODE:
                tableName = DbOpenHelper.GIRL_TABLE_NAME;
                break;
        }
        return tableName;
    }



    @Override
    public boolean onCreate() {
        context = getContext();
        initProviderData();
        return false;
    }

    //初始化原始数据
    private void initProviderData() {
        sqLiteDatabase = new DbOpenHelper(context).getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "男孩1");
        sqLiteDatabase.insert(DbOpenHelper.BOY_TABLE_NAME, null, contentValues);
        contentValues.put("name", "男孩2");
        sqLiteDatabase.insert(DbOpenHelper.BOY_TABLE_NAME, null, contentValues);
        contentValues.put("name", "男孩3");
        sqLiteDatabase.insert(DbOpenHelper.BOY_TABLE_NAME, null, contentValues);
        contentValues.clear();

        contentValues.put("name", "女孩1");
        sqLiteDatabase.insert(DbOpenHelper.GIRL_TABLE_NAME, null, contentValues);
        contentValues.put("name", "女孩2");
        sqLiteDatabase.insert(DbOpenHelper.GIRL_TABLE_NAME, null, contentValues);
        contentValues.put("name", "女孩3");
        sqLiteDatabase.insert(DbOpenHelper.GIRL_TABLE_NAME, null, contentValues);
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        return sqLiteDatabase.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        sqLiteDatabase.insert(tableName, null, values);
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int count = sqLiteDatabase.delete(tableName, selection, selectionArgs);
        if (count > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int row = sqLiteDatabase.update(tableName, values, selection, selectionArgs);
        if (row > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
