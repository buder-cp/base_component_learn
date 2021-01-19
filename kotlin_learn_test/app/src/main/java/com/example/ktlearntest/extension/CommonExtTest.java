package com.example.ktlearntest.extension;

public class CommonExtTest {
    public static void main(String[] args) {
        String special = CommonExtKt.getSpecial("bye-bye","666999");
        System.out.println(special);

        //在kt扩展函数中添加 @file:JvmName("Utils")
//        Utils.getSpecial("hehe", "987988");
    }
}
