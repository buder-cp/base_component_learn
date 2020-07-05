package com.test.patterns.create.factory.factoryMethod;

import com.test.patterns.entity.Bag;

/**
 * 包装工厂方法模式
 */
public interface BagFactory {
    public Bag getBag();//打包指令
}

