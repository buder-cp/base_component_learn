package com.test.patterns.create.factory.abstractFactory;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.bag.BananaBag;
import com.test.patterns.entity.fruit.Banana;

/**
 * 新香蕉工厂
 */
public class BananaFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Banana();
    }

    @Override
    public Bag getBag() {
        return new BananaBag();
    }
}
