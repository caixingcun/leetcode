package com.company.node;

/**
 * 链表相关
 */
public class NodeTest {




    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        //快慢指针 相遇


        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) { //相遇结束
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }



    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}

