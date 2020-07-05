package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Fruit;

/**
 * 工厂方法接口
 */
public interface FruitFactory {
    public Fruit getFruit();//摘水果指令
}
