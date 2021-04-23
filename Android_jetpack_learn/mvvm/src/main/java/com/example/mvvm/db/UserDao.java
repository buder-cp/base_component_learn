package com.example.mvvm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvm.model.User;

/**
 * DAO 对数据库表的增删改查
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Delete
    void deleteStudent(User user);

    @Query("SELECT * FROM user WHERE name = :name")
    LiveData<User> getUserByName(String name);
}
