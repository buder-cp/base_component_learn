package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.bag.BananaBag;

/**
 * 香蕉包装工厂方法模式
 */
public class BananaBagFactory implements BagFactory {
    public Bag getBag(){
        return new BananaBag();
    }
}
