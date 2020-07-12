package com.test.patterns.action.command.handler;

/**
 * 热门商品查询
 */
public class HotHandler {

    public String getHots(){
        System.out.println("返回热门商品列表");
        return "热门商品列表";
    }
}
