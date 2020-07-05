package com.test.patterns.create.builder;

import com.test.patterns.entity.fruit.Apple;
import com.test.patterns.entity.fruit.Banana;
import com.test.patterns.entity.fruit.Orange;

/**
 * 老会员水果套餐
 */
public class OldCustomerBuilder implements Builder {
    private FruitMeal fruitMeal = new FruitMeal();

    @Override
    public void buildApple(int price) {
        Apple apple = new Apple();
        apple.setPrice(price);
        fruitMeal.setApple(apple);
    }

    @Override
    public void buildBanana(int price) {
        Banana fruit = new Banana();
        fruit.setPrice(price);
        fruitMeal.setBanana(fruit);
    }

    @Override
    public void buildOrange(int price) {
        Orange fruit = new Orange("Peter",80);
        fruit.setPrice(price);
        fruitMeal.setOrange(fruit);
    }

    @Override
    public FruitMeal getFruitMeal() {
        fruitMeal.setDiscount(10);
        fruitMeal.init();
        return fruitMeal;
    }
}
