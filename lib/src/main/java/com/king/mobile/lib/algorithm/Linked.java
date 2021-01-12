package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
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
            assert slow.next != null;
            slow.next = slow.next.next;
            return header;
        }
    }

    /**
     * @param header 链表头
     */
    public static void sort(ListNode header) {

    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     * @param list1
     * @param list2
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list2 == null) return list1;
        if (list1 == null) return list2;
        ListNode newHead = null;
        ListNode newLast = null;
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                ListNode tem1 = list1;
                list1 = list1.next;
                tem1.next = null;
                if (newHead == null) {
                    newHead = tem1;
                } else {
                    newLast.next = tem1;
                }
                newLast = tem1;
            } else {
                ListNode tem2 = list2;
                list2 = list2.next;
                tem2.next = null;
                if (newHead == null) {
                    newHead = tem2;
                } else {
                    newLast.next = tem2;
                }
                newLast = tem2;
            }
        }
        if (list1 != null) {
            newLast.next = list1;
        }
        if (list2 != null) {
            newLast.next = list2;
        }
        return newHead;
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null||root2 ==null)return false;
        if(root1.val == root2.val){
            if(isSame(root1,root2)) {
                return true;
            }
        }
        return HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }
    boolean isSame(TreeNode root1,TreeNode root2){
        if(root2 == null)return true;
        if(root1 == null || root1.val != root2.val){
            return false;
        }else{
            return isSame(root1.left,root2.left)&&isSame(root1.right,root2.right);
        }
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

        ArrayList<Object> objects = new ArrayList<>();
    }

}
