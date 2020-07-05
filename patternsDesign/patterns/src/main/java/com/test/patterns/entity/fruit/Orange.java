package com.test.patterns.entity.fruit;

import com.test.patterns.entity.Fruit;

/**
 * 桔子
 */
public class Orange implements Fruit {
    private String name = "";
    private int price = 70;

    public Orange(String name, int price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.print("砂糖桔");
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
