package com.test.javabasic.annotation;

public class AnnotationDemo {
    @MyAnnotataion(name = "buder", website = "hello", revision = 1)
    public static void main(String[] args) {
        System.out.println("I am main method");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MyAnnotataion(name = "buder222", website = "hello222", revision = 2)
    public void demo() {
        System.out.println("I am demo method");
    }
}
