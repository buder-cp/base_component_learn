package com.test.patterns.entity.fruit;

import com.test.patterns.entity.Fruit;

/**
 * 香蕉
 */
public class Banana implements Fruit {

    private int price = 60;

    @Override
    public int price() {
        return price;
    }


    @Override
    public void draw() {
        System.out.print("仙人蕉");
    }


    public void setPrice(int price) {
        this.price = price;
    }
}
