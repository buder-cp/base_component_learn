package com.example.lib.jikexueyuan;

/**
 * 题目中的单链表节点
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        super();
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        super();
        this.val = val;
        this.next = next;
    }



    @Override
    public String toString() {
        return "ListNode [val=" + val + "]";
    }
}
