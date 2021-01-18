package com.example.ktlearntest.javaInvokeKotlin;

public class javaClass {

    private void getKt() {
        KtClassKt.joinToString("aaa");
        KtClassKt.joinToString("aaa", "bbb");
        KtClassKt.joinToString("aaa", "bbb", "ccc");
        KtClassKt.joinToString("aaa", "bbb", "ccc", "ddd");

//        Util.joinToString("aaa");
//        Util.joinToString("aaa", "bbb");
    }

    private void getInstance() {

        int a = MyUtils.Companion.getInstance();

        int b = MyUtils.getInstance();
    }

}
