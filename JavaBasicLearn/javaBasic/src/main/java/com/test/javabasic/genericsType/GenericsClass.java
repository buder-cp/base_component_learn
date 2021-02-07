package com.test.javabasic.genericsType;

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
class A extends GenericsClass<String, String> {

}

//也可以不指定具体的类型，如下：
class B extends GenericsClass {

}
