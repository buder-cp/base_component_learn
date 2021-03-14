package com.example.lib.jikexueyuan.链表中;

import com.example.lib.jikexueyuan.ListNode;

/**
 * 删除排序链表的重复节点
 *
 * 1-> 1 -> 2 -> 3
 *
 * 1->2->3
 *
 */

public class _083RemoveDuplicatesFromSortedList {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = head;
        ListNode p = head.next;
        while (p != null) {
            if (pre.val == p.val) {
                while (p != null && pre.val == p.val) {
                    p = p.next;
                }
                pre.next = p;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return head;
    }
}
