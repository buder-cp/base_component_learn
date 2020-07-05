package com.test.patterns.create.factory.origin;

import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.fruit.Apple;
import com.test.patterns.entity.fruit.Orange;

/**
 * 不使用设计模式：最原始的想法，哪里用就直接在哪里new出来相应的对象
 */
public class PeterClient {
    //Peter自己吃水果
    public static void main(String[] args) {
        peterdo();
        jamesdo();
        lisondo();
    }

    //Peter自己吃水果
    public static void peterdo() {
        Fruit fruit = new Apple();
        fruit.draw();
        //。。。直接啃着吃，吃掉了
        System.out.println("-----------------");
    }

    //送给james，切开吃
    public static void jamesdo() {
        Fruit fruit = new Apple();
        fruit.draw();
        //。。。切开吃
        System.out.println("-----------------");
    }

    //送给lison榨汁喝
    public static void lisondo() {
        Fruit fruit = new Orange("peter", 100);
        fruit.draw();
        //。。。榨汁运作
        System.out.println("-----------------");
    }
}

