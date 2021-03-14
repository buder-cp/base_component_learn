package com.example.lib.jikexueyuan.链表中;

import com.example.lib.jikexueyuan.ListNode;

/**
 * 寻找链表的倒数第N个节点
 */

public class findNFromLast {
    /**
     *取得链表的长度
     */
    public int lengthOfList(ListNode head) {
        int m = 0;
        ListNode p = head;
        while (p != null) {
            m++;
            p = p.next;
        }
        return m;
    }

    /**
     * 方法1
     */
    public ListNode find01(ListNode head, int n) {
        int len = lengthOfList(head);
        ListNode p = head;
        for (int i = 0; i <= len - n; i++) {
            p = p.next;
        }
        return p;
    }

    /**
     * 方法2，双指针，遍历一次
     */
    public ListNode find02(ListNode head, int n) {
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i <= n; i++) {
            p2 = p2.next;
        }

        while (p2 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        return p1;
    }


}
