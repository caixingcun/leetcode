import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache 双向链表 结合HashMap 完成 时间复杂度 O(1)操作
 */

public class LRUCache {

    private Map<Integer, Node> cache = new HashMap<>();
    private int size;
    private int cap;
    private Node head, tail;
    public LRUCache(int capacity) {
        this.size = 0;
        this.cap = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }


    private void addToHead(Node newNode) {
        //  head      <-newNode
        newNode.pre = head;
        //              newNode -> head.next
        newNode.next = head.next;
        //              newNode<- head.next
        head.next.pre = newNode;
        // head.next->  newNode
        head.next = newNode;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            ++size;
            if (size > cap) {
                Node tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    private Node removeTail() {
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }


}