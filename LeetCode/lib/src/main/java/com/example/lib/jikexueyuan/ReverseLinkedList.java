package com.example.lib.jikexueyuan;

public class ReverseLinkedList {

    public void printList(ListNode head) {
        ListNode p = head.next;
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
        System.out.println();
    }

    public ListNode arrayToList(int[] array){
        ListNode head=new ListNode(0);
        ListNode p=head;
        for(int value:array){
            p.next=new ListNode(value);
            p=p.next;
        }
        return head.next;
    }
    /**
     * 链表反转，非递归算法
     * 固定死的写法，记住
     */
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }else{
            ListNode pre=head;
            ListNode p=head.next;
            ListNode next=null;
            while(p!=null){
                next=p.next;
                p.next=pre;
                pre=p;
                p=next;
            }
            head.next=null;
            return pre;
        }
    }


}
