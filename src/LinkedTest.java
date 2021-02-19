import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.NodeList;
import sun.security.x509.EDIPartyName;

import javax.xml.soap.Node;
import java.util.List;

/**
 * 链表操作
 */
public class LinkedTest {

    public static void main(String[] args) {
        /**
         * test delete linkedNode last N Node
         */
//        ListNode root = new ListNode(0, null);
//        ListNode curr = root;
//        int[] arr = {1, 2, 3, 4, 5};
//        for (int i = 0; i < arr.length; i++) {
//            ListNode listNode = new ListNode(arr[i]);
//            curr.next = listNode;
//            curr = curr.next;
//        }
//        print(root.next);
//        System.out.println("------------");
//        ListNode listNode = removeNthFromEnd(root.next, 3);
//        print(listNode);

        int[] arr = {0, 2, 5};
        ListNode root = new ListNode(0, null);
        ListNode curr = root;
        for (int i = 0; i < arr.length; i++) {
            ListNode listNode = new ListNode(arr[i]);
            curr.next = listNode;
            curr = curr.next;
        }


    }

    /**
     * 以k个节点为一组 进行翻转 链表
     *
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // nextHead 指向链表中去除k个节点之后的头节点
        //初始指向节点head
        ListNode nextHead = head;
        int remainNum = 0;
        while (remainNum < k) {
            //链表中剩余节点不足k个
            // 无需翻转 直接返回 head
            if (nextHead== null) {
                return head;
            }
            remainNum++;
            nextHead = nextHead.next;
        }

        //递归 翻转 链表中去除前k和节点的后续节点
        ListNode subList = reverseKGroup(nextHead, k); //返回的数据 用head.next 指向
        //反转链表中前k个节点
        ListNode newHead = reverseTopN(head, k); //反转的数据只需要返回 无需节点操作
        //前k个节点 反转后 head 指向的节点 是反转后的链表中的最后一个节点
        head.next = subList;
        return newHead;
    }

    /**
     * 反转 链表 前n个节点
     * @param n
     * @param head
     * @return
     */
    public static ListNode reverseTopN(ListNode head,int n){
        ListNode pre = null;
        ListNode curr = head;
        for (int i = 0; i < n; i++) {
            //每一次循环只做一个节点方向变更
            //curr 为当前节点，pre 为前一个节点
            ListNode next = curr.next;  //记录下一个节点位置
            curr.next = pre; // 讲当前节点的next 回指向 pre
            pre = curr; //调整pre位置 到 当前位置
            curr = next; // 调整当前位置到 之前记录的下一节点
        }
        //最终的 pre 指向的是 变更前 的链表第n个 / 变更后 链表头
        return pre;
    }

    /**
     * 若干链表倒序
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            //移动curr 与 pre节点
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 交换链表 节点
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode(0, head);
        ListNode temp = root;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
        }
        return root.next;
    }


    /**
     * 升序链表数组合并为一个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode root = new ListNode(0);

        ListNode temp = root;
        for (int i = 0; i < lists.length; i++) {
            temp = mergeTwoLists(temp, lists[i]);
        }

        return root.next;
    }


    /**
     * 链表打印工具
     *
     * @param head
     */
    public static ListNode print(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode root = new ListNode(0);
        ListNode temp = root;
        temp.next = head;
        temp = temp.next;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
        return root.next;

    }

    /**
     * 删除链表倒数第n个节点
     * <p>
     * GIVEN    [1,2,3,4,5], n = 2
     * RETURN   [1,2,3,5]
     *
     * @param head
     * @param n    倒数第n个  n-1 .. n+1
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //追加一个头节点 方便返回
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode curr = dummy;
        for (int i = 1; i < length - n + 1; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return dummy.next;
    }

    public static int getLength(ListNode head) {
        int index = 0;
        while (head != null) {
            index++;
            head = head.next;
        }
        return index;
    }

    /**
     * 按顺序合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode temp = root;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        temp.next = (l1 == null ? l2 : l1);

        return root.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


