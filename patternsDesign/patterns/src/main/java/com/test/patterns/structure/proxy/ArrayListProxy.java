package com.test.patterns.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ArrayListProxy {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();
        ClassLoader classLoader = list.getClass().getClassLoader();
        Class<?>[] interfaces = list.getClass().getInterfaces();
        List<String> listProxy = (List<String>) Proxy.newProxyInstance(classLoader, interfaces,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(list, args);
                    }
                }
        );

        listProxy.add("i am proxy added");//我是动态代理对象添加的元素
        System.out.println(list);
    }
}
