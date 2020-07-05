package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Fruit;

/**
 * 工厂方法模式测试
 */
public class FactoryTest {
    private static FruitFactory fruitFactory;

    public static void main(String[] args) {
        //初始化苹果工厂
        fruitFactory = new AppleFactory();//spring配置

        peterdo();
        jamesdo();
        lisondo();
    }

    //Peter自己吃水果
    public static void peterdo() {
        Fruit fruit = fruitFactory.getFruit();
        fruit.draw();
        System.out.println("-----------------");
    }

    //james吃
    public static void jamesdo() {
        Fruit fruit = fruitFactory.getFruit();
        fruit.draw();
        System.out.println("-----------------");
    }

    //lison吃
    public static void lisondo() {
        Fruit fruit = fruitFactory.getFruit();
        fruit.draw();
        System.out.println("-----------------");
    }

}
