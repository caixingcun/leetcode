import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 快慢指针问题
 * 龟兔赛跑
 * 两个指针在数组 或者链表 序列上 移动速度不一样
 * 对于解决有环链表和数组特别有用
 * 有时不能用双指针 ，典型快慢指针模式，链表是否是回文
 */
public class FastSlowPointers {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode nodeList = new ListNode(arr[0]);
        ListNode root = nodeList;
        for (int i = 1; i < arr.length; i++) {
            nodeList.next = new ListNode(arr[i]);
            nodeList = nodeList.next;
        }
        nodeList.next = null;

//        ListNode head = root;
//        while (head.next != null) {
//            System.out.println(head.val + "");
//            head = head.next;
//        }
//        System.out.println(head.val + "");
        /**
         * Middle of the Linked List
         */
        ListNode listNode = middeNode(root);
        while (listNode.next != null) {
            System.out.println(listNode.val + "");
            listNode = listNode.next;
        }
        System.out.println(listNode.val + "");



    }

    /**
     * 环形链表
     * 判断链表是否经过相同的对象 ，使用去重容器Set来存储已经遍历的数据
     * 空间复杂度O(n) 时间负责都O(n)
     *
     * @return
     */
    private static boolean linkListCycle(ListNode head) {
        Set<Integer> pastSet = new HashSet<>();
        while (head != null) {
            if (pastSet.contains(head.val)) {
                return true;
            } else {
                pastSet.add(head.val);
                head = head.next;
            }
        }
        return false;
    }

    /**
     * 环形链表判断 空间复杂度O(1)实现
     * 不能创建新的Set
     * 用两个不等步长的指针，如果能遍历到下个节点是null 说明非循环链表，否则 步长相差1 肯定会相遇
     */

    private static boolean linkListCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slowNode = head;
        ListNode fastNode = head.next;
        while (slowNode != fastNode) {
            if (slowNode == null || fastNode.next == null) {
                return false;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return true;
    }

    /**
     * Middle of the Linked List
     *
     * @param head
     * @return
     */
    private static ListNode middeNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;

        while (fastNode != null && fastNode.next != null) {
            // 慢指针跑一步 快指针跑两步 直到快指针为空 说明单链表到头了  此时快指针在一半的位置
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        return slowNode;
    }

    /**
     * 链表节点
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public void add(ListNode nextNode) {
            next = nextNode;
        }
    }


}
