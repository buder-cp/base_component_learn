package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.fruit.Banana;

/**
 * 香蕉工厂方法模式
 */
public class BananaFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Banana();
    }
}
