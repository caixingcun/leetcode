package com.company.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 26 组合多个有序链表为一个
 */
public class LeetCode26 {
    public static void main(String[] args) {

    }

    public static class ListNodeCompare implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.value - o2.value;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        //创建一个小根堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeCompare());
        //将表头全部加入排序
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }

        if (heap.isEmpty()) {
            return null;
        }
        // 抓住一个表头
        ListNode head = heap.poll();
        // 创建一个操作指针 pre （操作指针最开始指向表头）
        ListNode pre = head;
        //操作指针的next 也就是第一个链表的next 不为空，将next加入根堆
        if (pre.next != null) {
            heap.add(pre.next);
        }
        // 循环执行 根堆不为空
        while (!heap.isEmpty()) {
            //根堆弹出最小链表节点
            ListNode curr = heap.poll();
            //将操作指针的next指向新节点
            pre.next = curr;
            //操作指针标记新节点
            pre = curr;
            //新节点next不为空 加入根堆
            if (curr.next != null) {
                heap.add(curr.next);
            }
        }
        // 返回最开始抓住的表头
        return head;
    }

}
