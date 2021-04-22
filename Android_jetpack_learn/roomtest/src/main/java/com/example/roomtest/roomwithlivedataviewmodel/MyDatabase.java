package com.example.roomtest.roomwithlivedataviewmodel;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomtest.roomdemo.database.Student;

@Database(entities = {Student.class}, exportSchema = false, version = 1)
public abstract class MyDatabase extends RoomDatabase
{
    private static final String DATABASE_NAME = "my_db";

    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getInstance(Context context)
    {
        if(databaseInstance == null)
        {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

    public abstract StudentDao studentDao();
}