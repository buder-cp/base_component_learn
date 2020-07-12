package com.test.patterns.action.command.command;


import com.test.patterns.action.command.Command;
import com.test.patterns.action.command.handler.HotHandler;

/**
 * 热门商品查询包装命令
 */
public class HotCommand extends Command {
    private HotHandler handler = new HotHandler();

    @Override
    public String execute() {
        return handler.getHots();
    }
}
