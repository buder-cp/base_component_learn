package com.test.javabasic.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class CASTest {
    // 类的成员变量
    static int data = 0;

    static Object lock = new Object();

    static Lock locks = new ReentrantLock();

    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                IntStream.range(0, 100).forEach(y -> {
//                    data++;
//                });
                //修改方式一：
//                IntStream.range(0, 100).forEach(y -> {
//                    synchronized (lock) {
//                        data++;
//                    }
//                });
//                IntStream.range(0, 100).forEach(y -> {
//                    locks.lock();
//                    data++;
//                    locks.unlock();
//                });
                IntStream.range(0, 100).forEach(y -> {
                    atomicInteger.incrementAndGet();
                });
            }).start();
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data);
        System.out.println(atomicInteger.get());
    }
}

