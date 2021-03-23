package com.test.javabasic.annotation.annotation_eraserse;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
//https://blog.csdn.net/coslay/article/details/41650855
public class Test {
    public List<String> list = new LinkedList<>();

    public static void main(String[] args) throws SecurityException, NoSuchFieldException {
        ParameterizedType pt = (ParameterizedType) Test.class.getField("list").getGenericType();
        System.out.println(pt.getActualTypeArguments().length);
        System.out.println(pt.getActualTypeArguments()[0]);
    }
}
