package com.example.mvvm.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm.MyApplication;
import com.example.mvvm.db.UserDao;
import com.example.mvvm.db.UserDatabase;
import com.example.mvvm.model.User;
import com.example.mvvm.repository.UserRepository;


public class UserViewModel extends AndroidViewModel {
    private LiveData<User> user;

    private UserRepository userRepository;

    private String userName = "MichaelYe";

    public UserViewModel(Application application) {
        super(application);
        UserDatabase database = MyApplication.getUserDatabase();
        UserDao userDao = database.userDao();
        userRepository = new UserRepository(userDao, MyApplication.getApi());
        user = userRepository.getUser(userName);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void refresh() {
        userRepository.refresh(userName);
    }
}
