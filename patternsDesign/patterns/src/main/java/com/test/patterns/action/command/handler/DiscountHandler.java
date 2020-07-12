package com.test.patterns.action.command.handler;

/**
 * 优惠商品查询
 */
public class DiscountHandler {
    public String getDiscounts(){
        System.out.println("返回优惠商品列表");
        return "优惠商品列表";
    }
}
