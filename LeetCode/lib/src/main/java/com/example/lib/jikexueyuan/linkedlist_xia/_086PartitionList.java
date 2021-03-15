package com.example.lib.jikexueyuan.linkedlist_xia;

import com.example.lib.jikexueyuan.ListNode;
/**
 链表划分，给定一个数x，比x小的在左边，比x大的在右边
 */
public class _086PartitionList {

    private static ListNode partition(ListNode head, int x) {
        ListNode leftHead = new ListNode(0);
        ListNode leftTail = leftHead;
        ListNode rightHead = new ListNode(0);
        ListNode rightTail = rightHead;
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                leftTail.next = p;
                leftTail = p;
            } else {
                rightTail.next = p;
                rightTail = p;
            }
            p = p.next;
        }
        leftTail.next = rightHead.next;
        rightTail.next = null;
        return leftHead.next;

    }

    public static void main(String[] args) {
        int[] array = {1, 4, 3, 2, 5, 2};
        int x = 3;
        ListNode head = ListNode.arrayToList(array);
        head = partition(head, x);
        ListNode.printList(head);
    }

}
