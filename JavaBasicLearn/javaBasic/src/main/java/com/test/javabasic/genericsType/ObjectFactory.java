package com.test.javabasic.genericsType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

//https://lrh1993.gitbooks.io/android_interview_guide/content/java/basis/reflection3.html
public class ObjectFactory {

    public static Object getInstance(String name) {
        try {
            //创建指定类对应的Class对象
            Class cls = Class.forName(name);
            //返回使用该Class对象创建的实例
            return cls.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getInstance(Class<T> cls) {
        try {
            //返回使用该Class对象创建的实例
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class TestObjectFactory {
    public static void main(String[] args) {
        //下面代码在编译时不会有任何问题，但是运行时将抛出ClassCastException异常，
        //因为程序试图将一个Date对象转换成String对象。
        Date date0 = (Date) ObjectFactory.getInstance("java.util.Date");
        String string0 = (String) ObjectFactory.getInstance("java.util.Date");

        //通过传入String.class便知道T代表String，所以返回的对象是String类型的，避免强制类型转换。
        Date date = (Date) ObjectFactory.getInstance(Date.class);
        String string = (String) ObjectFactory.getInstance(String.class);
    }
}


class GenericTest {
    private Map<String, Integer> score;
    public static void main(String[] args) throws Exception {
        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");
        // 直接使用getType()取出Field类型只对普通类型的Field有效
        Class<?> a = f.getType();
        // 下面将看到仅输出java.util.Map
        System.out.println("score 的类型是:" + a);
        // 获得Field实例f的泛型类型
        Type gType = f.getGenericType();
        // 如果gType类型是ParameterizedType对象
        if (gType instanceof ParameterizedType) {
            // 强制类型转换
            ParameterizedType pType = (ParameterizedType) gType;
            // 获取原始类型
            Type rType = pType.getRawType();
            System.out.println("RawType 原始类型是：" + rType);
            // 取得泛型类型的泛型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("ActualTypeArguments 泛型类型是:");
            for (int i = 0; i < tArgs.length; i++) {
                System.out.println("iiiii 第" + i + "个泛型类型是：" + tArgs[i]);
            }
        } else {
            System.out.println("error 获取泛型类型出错！");
        }
    }
}
