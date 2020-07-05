package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.Fruit;

/**
 * 水果店测试:
 * 使用工厂方法测试类，会产生打包错误的情况
 */
public class FruitStoreTest {

    private static FruitFactory fruitFactory;
    private static BagFactory bagFactory;

    public static void main(String[] args) {
        pack();
    }

    private static void pack() {
        //初始化苹果工厂
        fruitFactory = new AppleFactory();
        Fruit fruit = fruitFactory.getFruit();
        fruit.draw();

        //获取到了香蕉的包装
        bagFactory = new BananaBagFactory();
        Bag bag = bagFactory.getBag();

        //错误的把苹果放到了香蕉的包装中
        bag.pack();
    }
}
