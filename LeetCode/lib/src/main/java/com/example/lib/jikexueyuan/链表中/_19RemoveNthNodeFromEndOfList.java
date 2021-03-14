package com.example.lib.jikexueyuan.链表中;

import com.example.lib.jikexueyuan.ListNode;

/**
 *删除链表的倒数第N个节点
 */

public class _19RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        ListNode p = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        while (p != null) {
            p = p.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;

        return head;
    }
}
