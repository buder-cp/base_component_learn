package com.example.lib.jikexueyuan.linkedlist_zhong;

import com.example.lib.jikexueyuan.ListNode;

/**
 *删除链表的倒数第N个节点
 */

public class _19RemoveNthNodeFromEndOfList {
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 思路：找到倒数第N以及倒数第N的前一个，改变N前一个的后续指针
         */
        ListNode pre = head;
        ListNode p = head;
        int len = ListNode.lengthOfList(head);
        for (int i = 0; i < len - n -1; i++) {
            pre = pre.next;
        }
        for (int i = 0; i < len - n; i++) {
            p = p.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 6};
        ListNode head = ListNode.arrayToList(array1);
        head = removeNthFromEnd(head, 3);
        ListNode.printList(head);

//        System.out.println(removeNthFromEnd(head, 2));
    }
}
