package com.example.lib.jikexueyuan.linkedlist_xia;

import com.example.lib.jikexueyuan.ListNode;

public class _061RotateList {
    public static int lengthOfList(ListNode head){
        ListNode p=head;
        int n=0;
        while(p!=null){
            p=p.next;
            n++;
        }
        return n;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        /**
         * 思路：找到倒数第N个节点，改变两个指针即可
         */
        int len = lengthOfList(head);
        ListNode pre = head;
        int index = 1;
        while (index < len - k) {
            pre = pre.next;
            index++;//新的头结点的前一个node
        }
        ListNode newHead = pre.next;
        ListNode lastNode = pre.next;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = head;
        pre.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        int[] array1={1,2,3,4,5};
        ListNode head=ListNode.arrayToList(array1);
        head=rotateRight(head, 2);
        ListNode.printList(head);
//        int[] array2={1,2,3,4,5,6};
//        head=ListNode.arrayToList(array2);
//        head=rotateRight(head, 10);
//        ListNode.printList(head);
    }
}
