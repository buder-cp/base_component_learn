package com.test.patterns.action.command.handler;

/**
 * 最新商品查询
 */
public class NewerHandler {
    public String getNewers(){
        System.out.println("返回最新商品列表");
        return "最新商品列表";
    }
}
