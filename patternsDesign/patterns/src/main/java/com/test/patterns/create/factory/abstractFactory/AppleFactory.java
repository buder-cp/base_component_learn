package com.test.patterns.create.factory.abstractFactory;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.bag.AppleBag;
import com.test.patterns.entity.fruit.Apple;

/**
 * 新苹果工厂
 */
public class AppleFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Bag getBag() {
        return new AppleBag();
    }
}