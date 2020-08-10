package com.test.patterns.service.impl;

import com.test.patterns.service.OrderService;

/**
 * 海外订单
 */
public class OutOrderServiceImpl implements OrderService {
    @Override
    public int saveOrder() {
        System.out.println("order success：66666666");
        return 66666666;
    }

    @Override
    public void first() {
        System.out.println("first step");
    }

    @Override
    public int second() {
        System.out.println("second step");
        return 3;
    }

    @Override
    public void third() {
        System.out.println("third step");
    }
}
