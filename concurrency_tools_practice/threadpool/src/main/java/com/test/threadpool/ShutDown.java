package com.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }

        Thread.sleep(1500);
    }

    static class ShutDownTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
