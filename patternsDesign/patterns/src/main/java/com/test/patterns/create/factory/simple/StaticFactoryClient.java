package com.test.patterns.create.factory.simple;

import com.test.patterns.entity.Fruit;

public class StaticFactoryClient {
    public static void main(String[] args) {
        peterdo();
        jamesdo();
        lisondo();
    }

    //Peter获取香蕉方式一
    public static void peterdo() {
        Fruit fruit = StaticFactory.getFruit(StaticFactory.TYPE_BANANA);//调用方式一
        fruit.draw();
        //。。。直接啃着吃，吃掉了
        System.out.println("-----------------");
    }

    //James获取香蕉方式二
    public static void jamesdo() {
        Fruit fruit = StaticFactory.getFruitBanana();//调用方式二
        fruit.draw();
        //。。。切开吃
        System.out.println("-----------------");
    }

    //lison获取苹果
    public static void lisondo() {
        Fruit fruit = StaticFactory.getFruit(StaticFactory.TYPE_APPLE);
        fruit.draw();
        //。。。榨汁动作
        System.out.println("-----------------");
    }

}
