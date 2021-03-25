package com.test.opensourceframework.rxjava.define;

import rx.Observable;
import rx.functions.Func1;

public class MyTransformer implements Observable.Transformer<Integer,String> {
    public MyTransformer( /* any necessary params here */ ) {
        /* 这里添加必要的初始化代码 */
    }

    @Override
    public Observable<String> call(Observable<Integer> source) {
        /*
         * 这个简单的例子Transformer应用一个map操作，
         * 这个map操作将发射整数变换为发射整数的字符串表示。
         */
        return source.map( new Func1<Integer,String>() {
            @Override
            public String call(Integer t1) {
                return String.valueOf(t1);
            }
        } );
    }
}
