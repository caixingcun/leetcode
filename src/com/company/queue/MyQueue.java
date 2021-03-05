package com.company.queue;

import java.util.Stack;

/**
 * 用两个栈实现队列操作
 *
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

public class MyQueue {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
    }
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        // 将堆栈2 数据全部加入堆栈1
        // 加入堆栈1   倒序
        // 堆栈1 轮询 移入堆栈2 正序
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(x);

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        //堆栈2出栈
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        return -1;
    }

    /** Get the front element. */
    public int peek() {
        //堆栈2 peek
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        return -1;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        //堆栈2判空
        return stack2.isEmpty();
    }
}
