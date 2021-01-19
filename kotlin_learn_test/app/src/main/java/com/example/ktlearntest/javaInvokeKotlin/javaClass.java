package com.example.ktlearntest.javaInvokeKotlin;

public class javaClass {

    private void getKt() {
        KtClassKt.joinToString("aaa");
        KtClassKt.joinToString("aaa", "bbb");
        KtClassKt.joinToString("aaa", "bbb", "ccc");
        KtClassKt.joinToString("aaa", "bbb", "ccc", "ddd");

//        Util.joinToString("aaa");
//        Util.joinToString("aaa", "bbb");
    }

    private void getInstance() {

        int a = MyUtils.Companion.getInstance();

        int b = MyUtils.getInstance();
    }

    /**
     * Java文件中调用data class属性
     * 需要使用 @JvmField
     */
    private void getDataClass() {
        Person person = new Person("tom", 22, "1254365");
        person.name = "Jerry";
        person.age = 23;
        person.address = "海淀";
        person.setJerryPhone();
        person.getTomPhone("0653-789");
//        Person.Companion.getSex();
    }

}
