package com.company.listnode;

public class DeleteDuplicates {


    /**
     * 删除链表中重复元素
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        //        1   2   3   3    4    4   5
        //  last cur nex
        //       lst cur nex
        //           last cur nex
        //           last         next


        ListNode pre = head;
        if (pre == null) {
            return head;
        }
        ListNode cur = head.next;
        if (cur == null) {
            return head;
        }


        ListNode result = new ListNode(0);
        ListNode temp = result;
        boolean lastEqual = false;
        while (pre != null && cur != null) {
            if (pre.val == cur.next.val) {
                lastEqual = true;
            }else{
                if (lastEqual) {
                    lastEqual = false;
                }else{
                    temp.next = new ListNode(pre.val);
                    temp = temp.next;
                }
            }
            pre = cur;
            cur = cur.next;
        }
        if (!lastEqual) {
            temp.next = pre;
        }

        return result.next;
    }
}
