package com.example.observermodel;

import android.util.Log;

public class ConcreteObserver implements Observer {

    @Override
    public void update(MessageBean message) {
        Log.e("buder", message.getMessage() + " ");
    }
}
