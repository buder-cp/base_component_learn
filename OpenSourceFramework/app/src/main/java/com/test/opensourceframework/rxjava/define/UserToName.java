package com.test.opensourceframework.rxjava.define;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//https://www.jianshu.com/p/ae32500dfb07/
public class UserToName<String> implements ObservableOperator<String, User> {
    @Override
    public Observer apply(Observer observer) {

        return new Observer<User>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {

                mDisposable = d;
                observer.onSubscribe(d);
            }

            @Override
            public void onNext(User o) {

                if (!mDisposable.isDisposed()) {
                    observer.onNext(o.getName());
                }
            }

            @Override
            public void onError(Throwable e) {

                if (!mDisposable.isDisposed()) {
                    observer.onNext(e);
                }
            }

            @Override
            public void onComplete() {

                if (!mDisposable.isDisposed()) {
                    observer.onComplete();
                }
            }
        };
    }
}

class users {
    public static void main(String[] args) {
        User buder = new User();
        buder.setName("hehe");
        Disposable subscribe = Observable.just(buder)
                .lift(new UserToName<String>())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) {

                        System.out.println(s);
                    }
                });
    }
}
