package com.example.lib.jikexueyuan.linkedlist_zhong;

import com.example.lib.jikexueyuan.ListNode;


/**
 * 查询节点并删除
 */
public class _203RemoveLinkedListElements {
    private static ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode(0);//构造一个新的头空节点
        newHead.next = head;

        ListNode pre = newHead;
        ListNode p = head;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
                p = p.next;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 6, 6, 4, 6, 3, 5};
        ListNode head = ListNode.arrayToList(array1);
        head = removeElements(head, 6);
        ListNode.printList(head);
    }
}
