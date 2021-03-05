package com.test.javabasic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented//标记这些注解是否包含在用户文档中。
@Target(ElementType.METHOD)//标记这个注解应该是哪种 Java 成员。
@Retention(RetentionPolicy.RUNTIME)//标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问
public @interface MyAnnotataion {
    String name();
    String website() default "hello";
    int revision() default 1;
}
