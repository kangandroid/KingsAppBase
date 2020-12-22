package com.king.mobile.lib.algorithm;

import java.util.Arrays;

import javax.xml.soap.Node;

class Linked {
    static class ListNode {
        ListNode next;
        int value;

        public ListNode() {
        }

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode header) {
        if (header.next == null) return header;
        ListNode oldHeader = header;
        ListNode temHeader = null;
        while (oldHeader.next != null) {
            ListNode tem = oldHeader;
            oldHeader = oldHeader.next;//剪旧
            tem.next = temHeader; // 续新
            temHeader = tem; // 换头
        }
        oldHeader.next = temHeader;
        return oldHeader;
    }

    /**
     * 删除链表的倒数endIndex个节点
     *
     * @param header
     * @param endIndex
     * @return eg
     * 10->9->8->7->6->5->4->3->2->1
     * 10->9->8->7->6->4->3->2->1
     */
    public static ListNode removeFromEnd(ListNode header, int endIndex) {
        ListNode fast = header;
        ListNode slow = header;
        int index = 0;
        while (fast.next != null) {
            fast = fast.next;
            if (index == endIndex) {
                slow = slow.next;
            } else {
                index++;
            }
        }
        if (index < endIndex) {
            return null;
        } else {
            slow.next = slow.next.next;
            return header;
        }
    }

    /**
     *
     * @param header 链表头
     */
    public static void sort(ListNode header){

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode reverse = reverse(node1);
        do {
            System.out.println(reverse.value);
            reverse = reverse.next;
        }
        while (reverse.next != null);
        System.out.println(reverse.value);
    }

}
