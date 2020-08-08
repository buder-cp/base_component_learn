package com.test.threadpool;

public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("execute runnable");
        }
    }

}
