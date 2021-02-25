package com.company.listnode;

import java.util.List;

/**
 * 相交链表
 */
public class InterSectionNode {
    /**
     * 两条单链表 找出两条单链表 相交的起始节点
     * 因为是相交链表 起始节点后续节点都相等
     * 从后往前推 最后一个相等的节点 就是起始节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        //暴力破解

        while (headA != null) {
            ListNode temp = headB;
            while (temp != null) {
                if (temp == headA) {
                    return temp;
                }
            }
            headA = headA.next;
        }
        return null;
    }



}
