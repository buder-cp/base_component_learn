package com.example.annotationlearn.dynamic_runtime_annotation;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindIdApi {
    public static void bindId(Activity obj) {

        Class<? extends Activity> cls = obj.getClass();
        if (cls.isAnnotationPresent(BindId.class)) {
            BindId mId = (BindId) cls.getAnnotation(BindId.class);
            int id = mId.value();

            try {
                Method method = cls.getMethod("setContentView", int.class);

                method.setAccessible(true);
                method.invoke(obj, id);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindId.class)) {
                BindId mId = field.getAnnotation(BindId.class);
                int id = mId.value();
                try {

                    Method method = cls.getMethod("findViewById", int.class);
                    method.setAccessible(true);
                    Object view = method.invoke(obj, id);
                    field.setAccessible(true);
                    field.set(obj, view);


                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
