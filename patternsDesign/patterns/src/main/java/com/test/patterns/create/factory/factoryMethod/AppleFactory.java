package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.fruit.Apple;

/**
 * 苹果工厂方法模式
 */
public class AppleFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Apple();
    }
}
