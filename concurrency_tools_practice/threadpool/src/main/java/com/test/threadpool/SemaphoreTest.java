package com.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final Semaphore semaphore = new Semaphore(3);



        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }


}
