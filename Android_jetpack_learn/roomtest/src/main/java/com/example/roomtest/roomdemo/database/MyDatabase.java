package com.example.roomtest.roomdemo.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class}, exportSchema = false, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "my_db";

    private static MyDatabase databaseInstance;

    public abstract StudentDao studentDao();

    public static MyDatabase getInstance(Context context) {
        if (databaseInstance != null) {
            return databaseInstance;
        }

        synchronized (MyDatabase.class) {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MyDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        /**
                         * 我们可以在创建数据库时加入
                         * fallbackToDestructiveMigration该方法能够在现升级异常时 重新创建数
                         * 据表 要注意的是 虽然应用程序不会崩溃，但 数据表被重新 所有
                         * 的敛据也将会丢失
                         */
                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3, MIGRATION_3_4)
                        .build();
            }
        }
        return databaseInstance;
    }

    /**
     * 使用 Migration 升级数据库
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //do something
            Log.d("MyDatabase", "MIGRATION_1_2");
        }
    };

    private static Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //do something
            Log.d("MyDatabase", "MIGRATION_2_3");
        }
    };

    private static Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //do something
            Log.d("MyDatabase", "MIGRATION_1_3");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d("MyDatabase", "MIGRATION_3_4");
            database.execSQL("CREATE TABLE temp_Student (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "age TEXT)");
            database.execSQL("INSERT INTO temp_Student (id, name, age) " +
                    "SELECT id, name, age FROM Student");
            database.execSQL("DROP TABLE Student");
            database.execSQL("ALTER TABLE temp_Student RENAME TO Student");
        }
    };

    /**
     * 使用 Migration 升级数据库
     */

}