package com.test.patterns.create.vs_factory_strategy;

import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.fruit.Apple;
import com.test.patterns.entity.fruit.Banana;
import com.test.patterns.entity.fruit.Orange;

public class FruitStrategy {

    private Fruit fruit;

    private static final int TYPE_APPLE = 1;//苹果
    private static final int TYPE_ORANGE = 2;//桔子
    private static final int TYPE_BANANA = 3;//香蕉

    //调用方式一：直接传入具体水果实例类
    public FruitStrategy(Fruit fruit) {
        this.fruit = fruit;
    }

    //调用方式二：传入数字在策略类里new出具体水果
    public FruitStrategy(int type) {
        if (TYPE_APPLE == type) {
            this.fruit =  new Apple();
        } else if (TYPE_ORANGE == type) {
            this.fruit = new Orange("Peter", 80);
        } else if (TYPE_BANANA == type) {
            this.fruit = new Banana();
        }
    }

    public void draw() {
        this.fruit.draw();
    }

    public int price() {
        return this.fruit.price();
    }

}
