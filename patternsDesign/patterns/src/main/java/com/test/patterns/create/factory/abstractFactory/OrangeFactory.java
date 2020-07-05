package com.test.patterns.create.factory.abstractFactory;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.bag.OrangeBag;
import com.test.patterns.entity.fruit.Orange;

/**
 * 新桔子工厂
 */
public class OrangeFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Orange("Peter",50);
    }

    @Override
    public Bag getBag() {
        return new OrangeBag();
    }
}
