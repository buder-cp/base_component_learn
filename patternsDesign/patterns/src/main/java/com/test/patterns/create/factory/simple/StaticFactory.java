package com.test.patterns.create.factory.simple;

import com.test.patterns.entity.Fruit;
import com.test.patterns.entity.fruit.Apple;
import com.test.patterns.entity.fruit.Banana;
import com.test.patterns.entity.fruit.Orange;

public class StaticFactory {

    public static final int TYPE_APPLE = 1;//苹果
    public static final int TYPE_ORANGE = 2;//桔子
    public static final int TYPE_BANANA = 3;//香蕉

    /**
     * 调用方式一===========================================
     */
    public static Fruit getFruit(int type) {
        if (TYPE_APPLE == type) {
            return new Apple();
        } else if (TYPE_ORANGE == type) {
            return new Orange("Peter", 80);
        } else if (TYPE_BANANA == type) {
            return new Banana();
        }
        return null;
    }

    /**
     * 调用方式二===========================================
     * 多方法工厂
     */
    public static Fruit getFruitApple() {
        return new Apple();
    }

    public static Fruit getFruitOrange() {
        return new Orange("Peter", 80);
    }

    public static Fruit getFruitBanana() {
        return new Banana();
    }

}
