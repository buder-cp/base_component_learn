package com.example.observermodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> userList = new ArrayList<>();

    private static ConcreteSubject instance = new ConcreteSubject();
    public ConcreteSubject(){}
    public static ConcreteSubject getInstance() {
        return  instance;
    }

    @Override
    public void addObserver(Observer observer) {
        userList.add(observer);
        Log.d("########",userList.size() +"");
    }

    @Override
    public void notifyObserver(MessageBean message) {
        for (Observer observer : userList) {
            observer.update(message);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        userList.remove(observer);
    }

    @Override
    public void clearObserver() {
        userList.clear();
    }
}
