package com.test.opensourceframework.rxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUse {

    private Disposable mDisposable;

    public void selfFun() {
        final String TAG = "MainActivity";
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe 事件发射");
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return integer + "alan";
            }
        }).observeOn(AndroidSchedulers.mainThread())//观察者的执行线程
                .subscribeOn(Schedulers.io())//被观察者的执行线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe 成功");
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onSubscribe===" + s);
                        mDisposable.dispose();
                        Log.d(TAG, "切断观察者与被观察者的连接");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

}
