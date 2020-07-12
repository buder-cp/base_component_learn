package com.test.patterns.action.command.command;


import com.test.patterns.action.command.Command;
import com.test.patterns.action.command.handler.DiscountHandler;

/**
 * 优惠商品查询包装命令
 */
public class DiscountCommand extends Command {

    private DiscountHandler handler = new DiscountHandler();

    @Override
    public String execute() {
        return handler.getDiscounts();
    }
}
