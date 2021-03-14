package com.example.lib.guidezuochengyun;

import java.util.Stack;

public class pr1_getMin {
    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());
    }

    public static class MyStack {
        private final Stack<Integer> stackData = new Stack<>();
        private final Stack<Integer> stackMin = new Stack<>();

        public void push(int num) {
            stackData.push(num);
            if (stackMin.isEmpty()) {
                stackMin.push(num);
            } else {
                if (num <= stackMin.peek()) {
                    stackMin.push(num);
                } else {
                    stackMin.push(stackMin.peek());
                }
            }
        }

        public int pop() {
            stackMin.pop();
            return stackData.pop();
        }

        public int getMin() {
            return stackMin.peek();
        }
    }
}

