package com.company.listnode;

public class ReverseLinked {

    public ListNode reverseList(ListNode head) {
        //       1  -》 2 -》 3 -》 4
        //      head
        // pre   1  ->  2  -> 3 -> 4
        //      curr    next
        //    <-
        // pre = curr
        // curr = next
        // continue

        ListNode pre = null;
        ListNode curr = head;

        while(curr!=null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return  pre;

    }

}
