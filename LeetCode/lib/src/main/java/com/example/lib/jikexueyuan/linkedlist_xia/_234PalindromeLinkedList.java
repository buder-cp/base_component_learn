package com.example.lib.jikexueyuan.linkedlist_xia;

import com.example.lib.jikexueyuan.ListNode;

/**
 回文链表
https://www.cnblogs.com/keeya/p/9240256.html
 */
public class _234PalindromeLinkedList {

    private static boolean isPalindrome(ListNode head) {
        //1.找中间节点
        ListNode middleNode = findMiddleNode(head);
        //2.翻转中间节点之后链表
        ListNode newHead = ListNode.reverseList(middleNode);

        //3.比较两个链表的值即可
        while (newHead.next != null && head.next != null) {
            if (newHead.val != head.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }

    private static ListNode findMiddleNode(ListNode head) {
        ListNode pre = head;
        ListNode p = head;
        while (p.next != null && p.next.next != null) {
            pre = pre.next;
            p = p.next.next;
        }
        return p;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        ListNode head = ListNode.arrayToList(array1);
        System.out.println(isPalindrome(head));
//        ListNode.printList(isPalindrome(head));
//        int[] array2={1,2,3,4,1,2,3};
//        head=ListNode.arrayToList(array2);
//        System.out.println(isPalindrome(head));
    }

}
