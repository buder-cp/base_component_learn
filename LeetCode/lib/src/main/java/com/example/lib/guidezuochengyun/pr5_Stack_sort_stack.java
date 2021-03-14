package com.example.lib.guidezuochengyun;

import java.util.Stack;

public class pr5_Stack_sort_stack {
    private static void stackSorted(Stack<Integer> stack) {
        Stack<Integer> helper = new Stack<>();
        helper.push(stack.pop());
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!helper.isEmpty() && cur < helper.peek()) {
                stack.push(helper.pop());
            }
            helper.push(cur);
        }

        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        stackSorted(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }


}
