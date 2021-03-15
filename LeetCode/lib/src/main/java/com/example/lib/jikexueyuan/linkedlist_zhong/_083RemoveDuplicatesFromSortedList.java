package com.example.lib.jikexueyuan.linkedlist_zhong;

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
    private static ListNode deleteDuplicates(ListNode head) {
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

//    private static ListNode deleteDuplicates(ListNode head) {
//        ListNode pre = head;
//        ListNode p = head.next;
//        while (p != null) {
//            if (pre.val == p.val) {
//
//            }
//        }
//    }

    public static void main(String[] args) {
//        int[] array1 = {1, 1, 2, 2, 2, 3, 4,1};
        int[] array1 = {1, 2, 1, 2, 1, 2, 4,1};
        ListNode head = ListNode.arrayToList(array1);
        head = deleteDuplicates(head);
        ListNode.printList(head);
    }
}
