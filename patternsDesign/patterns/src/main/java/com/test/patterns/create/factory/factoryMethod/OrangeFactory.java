package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.fruit.Orange;

/**
 * 橘子工厂方法模式
 */
public class OrangeFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Orange("Peter",80);
    }
}
