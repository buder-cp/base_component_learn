package com.test.opensourceframework.rxjava;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public abstract class SimpleRxJavaObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {

    public abstract void _onSubscribe(@NonNull Disposable disposable);

    public abstract void _onNext(@NonNull T var1);

    public abstract void _onError(@NonNull Throwable e);

    public abstract void _onComplete();

    @Override
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.setOnce(this, d)) {
            try {
                _onSubscribe(this);
            } catch (Throwable var3) {
                Exceptions.throwIfFatal(var3);
                d.dispose();
                this.onError(var3);
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (!this.isDisposed()) {
            try {
                _onNext(t);
            } catch (Throwable var3) {
                Exceptions.throwIfFatal(var3);
                ((Disposable) this.get()).dispose();
                this.onError(var3);
            }
        }
    }


    @Override
    public void onError(Throwable t) {
        if (!this.isDisposed()) {
            this.lazySet(DisposableHelper.DISPOSED);

            try {
                _onError(t);
            } catch (Throwable var3) {
                Exceptions.throwIfFatal(var3);
                RxJavaPlugins.onError(new CompositeException(new Throwable[]{t, var3}));
            }
        } else {
            RxJavaPlugins.onError(t);
        }
    }

    @Override
    public void onComplete() {
        if (!this.isDisposed()) {
            this.lazySet(DisposableHelper.DISPOSED);

            try {
                _onComplete();
            } catch (Throwable var2) {
                Exceptions.throwIfFatal(var2);
                RxJavaPlugins.onError(var2);
            }
        }
    }

    @Override
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override
    public boolean isDisposed() {
        return this.get() == DisposableHelper.DISPOSED;
    }
}
