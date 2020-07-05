package com.test.patterns.entity.fruit;

import com.test.patterns.entity.Fruit;

/**
 * 苹果
 */
public class Apple implements Fruit {

    private int price = 100;

    public Apple() {

    }

    public Apple(int price) {
        this.price = price;
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.print("苹果红富士");
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
