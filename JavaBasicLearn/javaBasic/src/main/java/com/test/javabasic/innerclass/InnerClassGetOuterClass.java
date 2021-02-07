package com.test.javabasic.innerclass;

public class InnerClassGetOuterClass {
    public static void main(String[] args) {
        final String str="haha";
        new Thread() {
            @Override
            public void run() {
                System.out.println(str);
            }
        }.start();
    }
}
