package com.test.patterns.action.template;

import com.test.patterns.entity.Fruit;

import java.util.List;

/**
 * 模板方法模式
 * 其他人代付费用结算过程
 */
public class OtherPayShopping extends ShoppingCart{

    public OtherPayShopping(List<Fruit> products) {
        super(products);
    }

    @Override
    protected void pay(int money) {
        System.out.println("代付成功");
    }
}
