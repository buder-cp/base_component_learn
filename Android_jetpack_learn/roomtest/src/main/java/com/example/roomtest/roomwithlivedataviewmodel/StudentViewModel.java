package com.example.roomtest.roomwithlivedataviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomtest.roomdemo.database.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudent;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getInstance(application);
        liveDataStudent = myDatabase.studentDao().getStudentList();
    }

    public LiveData<List<Student>> getLiveDataStudent() {
        return liveDataStudent;
    }
}
