package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Bag;
import com.test.patterns.entity.bag.AppleBag;

/**
 * 苹果包装工厂方法模式
 */
public class AppleBagFactory implements BagFactory {
    public Bag getBag(){
        return new AppleBag();
    }
}
