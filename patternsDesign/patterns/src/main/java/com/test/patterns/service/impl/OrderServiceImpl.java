package com.test.patterns.service.impl;

import com.test.patterns.service.OrderService;

/**
 * 本地订单
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public int saveOrder() {
        System.out.println("下单成功，订单号： 888888");
        return 888888;
    }

    @Override
    public void first() {

    }

    @Override
    public int second() {
        return 32;
    }

    @Override
    public void third() {

    }
}
