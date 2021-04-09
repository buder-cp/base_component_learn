package com.example.ktlearntest;

import android.util.Log;

public class ServiceImplA {

    public String doSomething1(String s) {
//        Log.e("input is:{}", s);
        System.out.println("doSomething11,input:" + s);
        return s;
    }

    public String doSomething2(String s) {
//        Log.e("doSomething2,input:{}", s);
        System.out.println("doSomething22,input:" + s);
        return s;
    }
}
