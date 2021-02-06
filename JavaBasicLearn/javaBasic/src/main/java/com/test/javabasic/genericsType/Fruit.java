package com.test.javabasic.genericsType;

import java.util.ArrayList;
import java.util.List;

class Fruit {
    private int age;

    public Fruit() {
    }

    public Fruit(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Banana extends Fruit {
    private int age;

    public Banana() {
    }

    public Banana(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBananaMethodMethod() {
        return "bananaMethod";
    }
}

class Apple extends Fruit {
    private int age;

    public Apple() {
    }

    public Apple(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAppleMethod() {
        return "appleMethod";
    }

    public void addToList(List<? super Apple> list) {
        list.add(this);
    }
}

/*
泛型相关：
https://www.wanandroid.com/wenda/show/8821
https://lrh1993.gitbooks.io/android_interview_guide/content/java/basis/genericity.html
https://juejin.cn/post/6844903901552967688
 */

class Test {
    public static void main(String[] args) {
        /**
         * <? extends Fruit>
         * 作用：可以获取某个基类的子类的返回值，但是无法添加进去
         * 1、findViewById，传入id数值，返回不同类型view：TextView/ImageView/Button...
         * 2、TopBarFactory 获取BaseBar的子类
         */
        List<? extends Fruit> list = new ArrayList<>();
//        list.add(new Apple());//Error
//        Fruit fruit = list.get(0);//不报错

        System.out.println("tag_apple " + ((Apple) getApple()).getAge());
        System.out.println("tag_apple " + ((Apple) getApple()).getAppleMethod());
        System.out.println("tag_banana " + ((Banana) getBanana()).getAge());
        System.out.println("tag_banana " + ((Banana) getBanana()).getBananaMethodMethod());


        //<? extends Fruit>
        List<Apple> listApple = new ArrayList<>();
        listApple.add(new Apple(1));
        List<Banana> listBanana = new ArrayList<>();
        listBanana.add(new Banana(2));
        int totalAge = getTotalWeight(listApple) + getTotalWeight(listBanana);//编译报错
        System.out.println("tag_totalAge " + totalAge);

        /**
         * <? super Fruit>
         * 作用：添加子类，但是无法获取出来
         *
         */
        List<Fruit> fruits = new ArrayList<>();
        Apple apple = new Apple();
        List<? super Apple> lists = new ArrayList<>();
        lists.add(apple);
        fruits.add(apple);
        apple.addToList(fruits);

//        List<? super Fruit> list2 = new ArrayList<>();
//        list2.add(new Apple());//不报错
//        Fruit object = list2.get(0);//Error

    }
//***************************************<T extends E> 方法上，约束返回值类型****************************************
    /**
     * <T extends E> 和 <? extends E> 有什么区别
     * 它们用的地方不一样：
     * < T extends E>只能用于形参（也就是泛型定义的时候）
     * <? extends E>只能用于实参（也就是传入具体泛型类型的时候）
     *
     * 用于方法的返回值类型时，约束了返回值类型
     */
    private static <T extends Fruit> T getApple() {
        Apple apple = new Apple();
        apple.setAge(12);
        return (T) apple;
    }

    private static <V extends Fruit> V getBanana() {
        Banana banana = new Banana(34);
        return (V) banana;
    }
//*******************************************<? extends Fruit> 方法上，入参类型************************************
    /**
     * https://juejin.cn/post/6844903901552967688
     * <T extends E> 和 <? extends E> 有什么区别
     * 它们用的地方不一样：
     * < T extends E>只能用于形参（也就是泛型定义的时候）
     * <? extends E>只能用于实参（也就是传入具体泛型类型的时候）
     */
    public static int getTotalWeight(List<? extends Fruit> list) {
        int totalWeight = 0;
        for (int i = 0; i < list.size(); i++) {
            Fruit fruit = list.get(i);
            totalWeight += fruit.getAge();
        }
//        list.add(new Apple(12));//不能把元素加入到其中。因为程序无法确定c集合中元素的类型，所以不能向其添加对象。
        return totalWeight;
    }


}