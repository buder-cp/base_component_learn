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
         */
        List<? extends Fruit> list = new ArrayList<>();//前面是实参，后面是形参
//        list.add(new Banana());//Error，不能添加的原因：
        //我们知道实参list是Fruit，假设这个list的形参是苹果，但是list.add(香蕉/橘子)就肯定是错误的，
        //无法添加的原因：list内添加的对象必须是Fruit的子类，那么，list可以添加苹果、香蕉、橘子等，肯定错误
        //因此无法添加子类对象
        Fruit fruit = list.get(0);//可以获取元素

        /**
         * <T extends Fruit>，用于形参，限定返回值
         * 只能获取，无法添加
         */
        System.out.println("tag_apple " + ((Apple) getApple()).getAge());
        System.out.println("tag_apple " + ((Apple) getApple()).getAppleMethod());
        System.out.println("tag_banana " + ((Banana) getBanana()).getAge());
        System.out.println("tag_banana " + ((Banana) getBanana()).getBananaMethodMethod());

        /**
         * <? extends Fruit>，用于实参，限定传入参数
         * 只能获取，无法添加
         */
        List<Apple> listApple = new ArrayList<>();
        listApple.add(new Apple(1));
        List<Banana> listBanana = new ArrayList<>();
        listBanana.add(new Banana(2));
        int totalAge = getTotalWeight(listApple) + getTotalWeight(listBanana);//编译报错
        System.out.println("tag_totalAge " + totalAge);

        /**
         * <? super Fruit>
         * 作用：添加子类，但是无法获取出来
         * 只能用于实参，即作为参数传递，只能添加，无法获取
         */
        List<Fruit> fruits = new ArrayList<Fruit>();
        Apple apple = new Apple();
        List<? super Apple> lists = new ArrayList<>();
        lists.add(apple);
        fruits.add(apple);
        apple.addToList(fruits);
        apple.addToList(lists);

        List<? super Fruit> list2 = new ArrayList<Fruit>();
        list2.add(new Apple());//不报错
//        Apple apple = list2.get(0);//ERROR
        //错误原因：get到基类是Fruit及其父类，无法判断获取到的类型具体是苹果/香蕉/橘子的具体，
        //而只能知道是Fruit
        Object object = list2.get(0);//这里不是具体类型，而是Object，然后根据类型判断
        if (object instanceof Apple) {
            System.out.println("list2 Apple: " + ((Apple)object).getAppleMethod());
        } else if (object instanceof Fruit) {
            System.out.println("list2 Fruit:" + ((Fruit)object).getAge());
        }

    }


//*******************************************<T extends Fruit> 用于方法定义****************************************
//*******************************************<? extends Fruit> 用于方法调用传参************************************

    /**
     * https://juejin.cn/post/6844903901552967688
     * <T extends E> 和 <? extends E> 有什么区别
     * 它们用的地方不一样：
     * <T extends E>只能用于形参（也就是泛型定义的时候），限定返回值
     * <? extends E>只能用于实参（也就是传入具体泛型类型的时候），限定传入参数，不能添加元素
     * <p>
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

    /**
     * <T extends E> 和 <? extends E> 有什么区别
     * 它们用的地方不一样：
     * <T extends E>只能用于形参（也就是泛型定义的时候），限定返回值
     * <? extends E>只能用于实参（也就是传入具体泛型类型的时候），限定传入参数，不能添加元素
     */
    public static int getTotalWeight(List<? extends Fruit> list) {
        int totalWeight = 0;
        for (int i = 0; i < list.size(); i++) {
            Fruit fruit = list.get(i);
            totalWeight += fruit.getAge();
        }

        // 不能把元素加入到其中。因为程序无法确定list集合中元素的类型，所以不能向其添加对象。
        // list.add(new Apple(12));//会编译报错，因为传入的是Fruit父类list，不确定是苹果、香蕉还是橘子，
        //假设list是苹果，但是我们list.add(香蕉/橘子)，这就是错误的，因此无法添加子类对象

        Fruit fruit = list.get(0);
        if (fruit instanceof Apple) {
            System.out.println("pingguo:" + ((Apple) fruit).getAppleMethod());
        } else if (fruit instanceof Banana) {
            System.out.println("xiangjiao:"  + ((Banana) fruit).getBananaMethodMethod());
        }

        return totalWeight;
    }
//*******************************************************************************************************************

}