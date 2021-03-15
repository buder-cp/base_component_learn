package com.example.lib.jikexueyuan.linkedlist_shang;

/**
 * 链表节点
 */
public class ListNode<T> {
    /**
     * 值
     */
    public T value;
    /**
     * 指向下一个节点的指针(引用)
     */
    public ListNode<T> next;
    public ListNode(T value, ListNode<T> next) {
        super();
        this.value = value;
        this.next = next;
    }
    public ListNode() {
        super();
    }
    /**
     * 指向前趋节点的指针，用于双链表
     */
    public ListNode<T> pre;
}
