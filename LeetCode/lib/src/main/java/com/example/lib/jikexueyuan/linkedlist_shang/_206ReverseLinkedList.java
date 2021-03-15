package com.example.lib.jikexueyuan.linkedlist_shang;
/**
 * 链表反转，非递归算法
 */
public class _206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = head;
        ListNode p = head.next;
        ListNode next = null;
        while (p != null) {
            next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        head.next = null;
        return pre;
    }
}
