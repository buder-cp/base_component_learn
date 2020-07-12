package com.test.patterns.structure.facade;

/**
 * 客户服务一条龙
 */
public class OrderFacade {
    private PickService pickService;
    private PackService packService;

    public OrderFacade(){
        pickService = new PickService();
        packService = new PackService();
    }

    /**
     * 客户订单处理类
     */
    public void doOrder(){
        //采摘
        System.out.println("--------------");
        pickService.doPick();
        //包装
        System.out.println("--------------");
        packService.doPack();
        //快递
        System.out.println("--------------");
        System.out.println("本次快递目的地：北京-朝阳区-三里屯");
    }
}
