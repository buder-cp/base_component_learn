package com.example.lib.guidezuochengyun;

import java.util.Stack;

public class pr2_twoStack_queue {
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(3);
        test.add(2);
        test.add(8);
        test.add(1);
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
    }

    public static class TwoStacksQueue {
        private final Stack<Integer> stackPush = new Stack<>();
        private final Stack<Integer> stackPop = new Stack<>();

        public void add(int num) {
            stackPush.push(num);
        }

        public int poll() {
            if (stackPop.isEmpty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

    }

}
