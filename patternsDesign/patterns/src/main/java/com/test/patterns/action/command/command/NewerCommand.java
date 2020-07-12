package com.test.patterns.action.command.command;


import com.test.patterns.action.command.Command;
import com.test.patterns.action.command.handler.NewerHandler;

/**
 * 最新商品查询包装命令
 */
public class NewerCommand extends Command {
    private NewerHandler handler = new NewerHandler();

    @Override
    public String execute() {
        return handler.getNewers();
    }
}
