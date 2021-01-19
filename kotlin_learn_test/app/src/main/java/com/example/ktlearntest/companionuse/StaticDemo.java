package com.example.ktlearntest.companionuse;

//https://www.jianshu.com/p/e8752c880088

public class StaticDemo {

    //静态常量
    public static final String LOAN_TYPE = "loanType";
    public static final String LOAN_TITLE = "loanTitle";

    //静态方法
    public static void test() {
    }

    /**
     * java中调用kotlin静态变量
     */
    private void testKotlinField() {
        String type_1 = StaticDemoActivity.TYPE_1;
        String type_3 = StaticDemoActivity.getTYPE_3();
        String type_2 = StaticDemoActivity.Companion.getTYPE_2();
    }

    /**
     * java中调用kotlin静态函数
     */
    private void testKotlinFun() {

        StaticDemoActivity.Companion.test();

        //添加了 @JVMStatic
        StaticDemoActivity.test2222();
    }

}

