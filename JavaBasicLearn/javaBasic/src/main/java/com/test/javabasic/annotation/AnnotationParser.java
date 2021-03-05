package com.test.javabasic.annotation;

import java.lang.reflect.Method;

public class AnnotationParser {
    public static void main(String[] args) throws SecurityException, ClassNotFoundException {
        String clazz = "com.test.javabasic.annotation.AnnotationDemo";
        Method[] demoMethod = AnnotationParser.class.getClassLoader().loadClass(clazz).getMethods();
        for (Method method : demoMethod) {
            if (method.isAnnotationPresent(MyAnnotataion.class)) {
                MyAnnotataion annotationInfo = method.getAnnotation(MyAnnotataion.class);
                System.out.println("method: "+ method);
                System.out.println("name= "+ annotationInfo.name() +
                        " , website= "+ annotationInfo.website()
                        + " , revision= "+annotationInfo.revision());
            }
        }
    }
}
