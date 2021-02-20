package com.king.mobile.lib.algorithm;

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
     *
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





    public static void main(String[] args) {

    }

}
