package com.example.annotationlearn.dynamic_runtime_annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindOnClickApi {
    public static void bindOnClick(final Activity obj) {
        Class<?> cls = obj.getClass();
        //获取当前Activity的所有方法，包括私有
        Method methods[] = cls.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            if (method.isAnnotationPresent(BindOnClick.class)) {
                // 得到这个类的OnClick注解
                BindOnClick mOnClick = (BindOnClick) method.getAnnotation(BindOnClick.class);
                // 得到注解的值
                int[] id = mOnClick.value();
                for (int j = 0; j < id.length; j++) {
                    final View view = obj.findViewById(id[j]);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //反射指定的点击方法
                            try {
                                //私有方法需要设置true才能访问
                                method.setAccessible(true);
                                method.invoke(obj, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }
}
