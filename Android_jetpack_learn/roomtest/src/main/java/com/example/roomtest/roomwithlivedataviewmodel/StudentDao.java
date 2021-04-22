package com.example.roomtest.roomwithlivedataviewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtest.roomdemo.database.Student;

import java.util.List;

/**
 * DAO
 * <p>
 * 对数据库表的增删改查
 */
@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getStudentList();//希望监听学生表的变化，为其加上LiveData

    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(int id);
}

