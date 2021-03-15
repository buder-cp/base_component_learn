package com.example.lib.jikexueyuan.linkedlist_shang;

import java.util.Comparator;
import java.util.Stack;

/**
 * 泛型版本的单链表
 */
public class MiniList<T> {
    /**
     * 默认的头结点
     */
    private ListNode<T> head = new ListNode<T>(null, null);
    /**
     * 比较器
     */
    public Comparator<T> comp;

    /**
     * 比较a和b的大小
     * 如果a==b,返回0
     * 如果a<b,返回负数
     * 如果a>b,返回正数
     */
    @SuppressWarnings("unchecked")
    public int compare(T a, T b) {
        if (comp != null) {
            return comp.compare(a, b);
        } else {
            Comparable<T> c = (Comparable<T>) a;
            return c.compareTo(b);
        }
    }

    /**
     * 取得链表的最大值
     */
    public T getMax() {
        if (head.next == null) {
            return null;
        }
        ListNode<T> p = head.next;
        T max = p.value;
        p = p.next;
        while (p != null) {
            if (compare(p.value, max) > 0) {
                max = p.value;
            }
            p = p.next;
        }
        return max;
    }

    /**
     * 数组转换成链表
     */
    public void arrayToList(T[] array) {
        ListNode<T> p = head;
        for (T t : array) {
            ListNode<T> node = new ListNode<T>(t, null);
            p.next = node;
            p = node;
        }
    }

    /**
     * 打印链表
     */
    public void printList() {
        ListNode<T> p = head.next;//头结点不需要打印，永远为null
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 插入
     */
    public void insert(int index, T value) {
        ListNode<T> p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        ListNode<T> node = new ListNode<T>(value, null);
        node.next = p.next;
        p.next = node;
    }

    /**
     * 删除
     */
    public T remove(int index) {
        ListNode<T> pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode<T> p = pre.next;
        pre.next = p.next;
        return p.value;
    }

    /**
     * 查询
     */
    public T get(int index) {
        ListNode<T> p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.value;
    }

    /**
     * 修改
     */
    public void set(int index, T value) {
        ListNode<T> p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        p.value = value;
    }

    /**
     * 逆序打印链表，非递归算法
     */
    public void printInverse() {
        if (head.next == null) {
            return;
        }
        Stack<T> stack = new Stack<T>();
        ListNode<T> p = head.next;
        while (p != null) {
            stack.push(p.value);
            p = p.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    /**
     * 逆序打印链表，递归算法
     */
    public void printInverseRecursive() {
        if (head.next == null) {
            return;
        }
        recursive(head.next);
        System.out.println();
    }

    private void recursive(ListNode<T> p) {
        if (p != null) {
            recursive(p.next);
            System.out.print(p.value + " ");
        }
    }
}
