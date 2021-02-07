package com.test.javabasic.innerclass;

interface Inner {
    void setW();
}

public class Outer {
    public int w = 100;

    public Inner getInner(final int x) {
        final int y = 100;
        return new Inner() {
            @Override
            public void setW() {
                int a = x + y;
            }
        };
    }

    public Inner getInner() {
        return new Inner() {
            // 这里可以修改外部类的全局变量
            public void setW() {
                w = 12;//持有外部类的引用，这里又是全局变量，因此无需final，即可保证是同一个值
            }
        };
    }
}

class test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.getInner().setW();
        System.out.println(outer.w);
    }
}
