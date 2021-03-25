package com.test.opensourceframework.rxjava.define;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
//https://www.jianshu.com/p/ae32500dfb07/
public class UserTransformer implements ObservableTransformer<User, String> {
    @NonNull
    @Override
    public ObservableSource<String> apply(@NonNull Observable<User> upstream) {
        return upstream.flatMap(new Function<User, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull User user) throws Exception {
                return Observable.fromArray(user.getName());
            }
        });
    }
}

class user{
    public static void main(String[] args) {
        User buder = new User();
        buder.setName("hehe");
        Disposable subscribe = Observable.just(buder)
                .compose(new UserTransformer())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }
}
