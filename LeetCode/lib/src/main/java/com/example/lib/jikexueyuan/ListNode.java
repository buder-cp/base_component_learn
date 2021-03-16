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

    public static void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
        System.out.println();
    }

    public static ListNode arrayToList(int[] array) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int value : array) {
            p.next = new ListNode(value);
            p = p.next;
        }
        return head.next;
    }

    /**
     * 取得链表的长度
     */
    public static int lengthOfList(ListNode head) {
        int m = 0;
        ListNode p = head;
        while (p != null) {
            m++;
            p = p.next;
        }
        return m;
    }

    /**
     * 链表反转
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre=head;
        ListNode p=pre.next;
        ListNode next;
        while(p!=null){
            next=p.next;
            p.next=pre;
            pre=p;
            p=next;
        }
        head.next=null;
        return pre;
    }

    /**
     * 链表中间节点
     */
    private static ListNode getMiddleNode(ListNode head) {
        ListNode pre = head;
        ListNode p = head;
        while (p.next != null && p.next.next != null) {
            pre = pre.next;
            p = p.next.next;
        }
        return pre;
    }

}
