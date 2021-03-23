package com.test.javabasic.annotation.annotation_eraserse;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
//https://www.zhihu.com/question/346911525/answer/830285753
public class ClassTest {

    class Foo<E extends CharSequence> {
        public List<Bar> children = new ArrayList<Bar>();

        public List<StringBuilder> foo(List<String> foo) {
            return null;
        }

        public void bar(List<? extends String> param) {
        }
    }

    class Bar extends Foo<String> {
    }

    public static void main(String[] args) throws Exception {
        ParameterizedType type = (ParameterizedType) Bar.class.getGenericSuperclass();
        System.out.println(type.getActualTypeArguments()[0]);

        ParameterizedType fieldType = (ParameterizedType) Foo.class.getField("children").getGenericType();
        System.out.println(fieldType.getActualTypeArguments()[0]);

        ParameterizedType paramType = (ParameterizedType) Foo.class.getMethod("foo", List.class).getGenericParameterTypes()[0];
        System.out.println(paramType.getActualTypeArguments()[0]);

        System.out.println(Foo.class.getTypeParameters()[0].getBounds()[0]);
    }
}

/**
 * class java.lang.String
 * class com.test.javabasic.annotation.annotation_eraserse.ClassTest$Bar
 * class java.lang.String
 * interface java.lang.CharSequence
 */
