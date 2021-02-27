package com.company.listnode;

import java.util.Stack;

public class IsPalindrome {
    /**
     * 234 回文链表
     * 1>2>2>1
     */

    public boolean isPalindrome(ListNode head) {

        //快慢指针
        //找到中间位置
        //开始-中间 丢入堆栈
        // 慢指针从中间位置轮训，堆栈出站
        //慢指针 将节点挨个加入堆栈，快指针轮训 ，快指针到头 慢在

        Stack<ListNode> stack = new Stack<>();

        if (head == null) {
            return true;
        }
        if (head.next == null) {
            return true;
        }

        //  1 2 3
        //  S F
        //    S   F

        //  1 2 3 4
        //  S F
        //    S   F

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null) {
            stack.push(slow);
            if (fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                break;
            }
        }

        slow = slow.next;

        while (slow != null && !stack.isEmpty() && stack.peek().val == slow.val) {
            slow = slow.next;
            stack.pop();
        }

        return stack.isEmpty();
    }
}
