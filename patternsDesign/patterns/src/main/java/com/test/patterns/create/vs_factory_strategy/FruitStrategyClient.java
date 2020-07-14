package com.test.patterns.create.vs_factory_strategy;

import com.test.patterns.entity.fruit.Apple;

public class FruitStrategyClient {
    public static void main(String[] args) {
        //调用方式一：直接传入具体水果实例类
        FruitStrategy fruit = new FruitStrategy(new Apple());
        fruit.draw();

        //调用方式二：传入数字在策略类里new出具体水果
        FruitStrategy strategy = new FruitStrategy(1);
        strategy.draw();
    }
}
