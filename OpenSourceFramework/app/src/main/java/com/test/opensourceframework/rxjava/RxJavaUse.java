package com.test.opensourceframework.rxjava;

import android.util.Log;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUse extends RxAppCompatActivity {

    private Disposable mDisposable;

    public void selfFun() {
        final String TAG = "MainActivity";
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe 事件发射");
                emitter.onNext(1);
                emitter.onComplete();
                emitter.onError(new Throwable("test"));
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer + "alan";
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                saveIt();//保存一下
            }
        }).observeOn(AndroidSchedulers.mainThread())//观察者的执行线程
                .subscribeOn(Schedulers.io())//被观察者的执行线程
                .compose(bindUntilEvent(ActivityEvent.DESTROY))//方式一
                .compose(bindToLifecycle())//方式二
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

    private void saveIt() {

    }

}
