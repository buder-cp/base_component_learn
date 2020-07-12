package com.test.patterns.structure.facade;

/**
 * 客户服务一条龙
 */
public class CustomerClient {

    public static void main(String[] args){
        doOrderFacade();
    }

    public static void doOrderFacade(){
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.doOrder();
    }

}
