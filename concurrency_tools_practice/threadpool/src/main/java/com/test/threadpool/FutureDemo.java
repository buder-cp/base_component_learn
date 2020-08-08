package com.test.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class FutureDemo {

    static ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        try {
            futureWithRunnable();
            futureWithCallable();
            futureTask();
        } catch (Exception e) {
        }
    }

    private static void futureTask() {
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return fibc(20);
            }
        });
        mExecutor.submit(futureTask);
    }

    private static void futureWithCallable() {
        Future result2 = mExecutor.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                return fibc(20);
            }
        });
    }

    private static void futureWithRunnable() {
        Future result = mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                fibc(20);
            }
        });
    }

    // 效率底下的斐波那契数列, 耗时的操作
    private static int fibc(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }
}
