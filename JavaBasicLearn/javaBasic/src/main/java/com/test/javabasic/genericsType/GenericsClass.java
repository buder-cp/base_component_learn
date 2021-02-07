package com.test.javabasic.genericsType;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;

import sun.net.www.content.text.Generic;

//泛型类定义
//在泛型类、泛型接口的方法中，把泛型中声明的类型形参当成普通类型使用
public class GenericsClass<K, V> {
    private K key;
    private V value;

    public GenericsClass() {
    }

    public GenericsClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

//泛型类使用
class UseTest {
    public static void main(String[] args) {
        GenericsClass<String, String> genericsClass = new GenericsClass<>("hhehe", "21321");
        GenericsClass<Integer, String> genericsClass1 = new GenericsClass<>(1213, "21321");
        GenericsClass<String, Double> genericsClass2 = new GenericsClass<>("hhehe", 2.333);

        System.out.println(genericsClass.getKey() + genericsClass.getValue());
        System.out.println(genericsClass1.getKey() + genericsClass1.getValue());
        System.out.println(genericsClass2.getKey() + genericsClass2.getValue());
    }
}

//泛型类派生子类
//当创建了带泛型声明的接口、父类之后，可以为该接口创建实现类，
// 或者从该父类派生子类，需要注意：使用这些接口、父类派生子类时不能再包含类型形参，
// 需要传入具体的类型。
// 保留父类泛型 ----》泛型子类
// 1）全部保留
class A<K, V> extends GenericsClass<K, V> {

}

// 2) 部分保留
class B<K> extends GenericsClass<K, String> {

}

// 不保留父类泛型 -----》子类按需实现
// 1)具体类型
class C extends GenericsClass<String, Integer> {

}

// 2)没有具体类型
// 泛型擦除：实现或继承父类的子类，没有指定类型，类似于Object
class D extends GenericsClass {

}
