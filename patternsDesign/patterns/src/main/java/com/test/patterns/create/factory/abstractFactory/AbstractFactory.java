package com.test.patterns.create.factory.abstractFactory;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.Fruit;

/**
 * 抽象水果工厂
 */
public abstract class AbstractFactory {
    public abstract Fruit getFruit();

    public abstract Bag getBag();

}
