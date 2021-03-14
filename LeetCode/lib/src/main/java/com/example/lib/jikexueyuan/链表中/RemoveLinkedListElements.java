package com.example.lib.jikexueyuan.链表中;

import com.example.lib.jikexueyuan.ListNode;


/**
 * 查询节点并删除
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = head;
        ListNode p = head.next;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
                p = p.next;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return pre.next;
    }
}
