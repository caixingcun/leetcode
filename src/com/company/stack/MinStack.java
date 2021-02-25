package com.company.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 * <p>
 * 最小栈实现 支持push pop top 在常数时间内检索到最小元素 // 排序 不能遍历 Tree
 */

public class MinStack {
    /**
     * initialize your data structure here.
     */
    TreeMap<Integer, Integer> treeMap;
    List<Integer> list;

    public MinStack() {
        treeMap = new TreeMap();
        list = new ArrayList<>();
    }

    public void push(int x) {
        treeMap.put(x, treeMap.getOrDefault(x, 0) + 1);
        list.add(x);
    }

    public void pop() {
        if (list.size() == 0) {
            return;
        }
        Integer x = list.remove(list.size() - 1);
        treeMap.put(x, treeMap.getOrDefault(x, 0) - 1);
        if (treeMap.get(x)==0) {
            treeMap.remove(x);
        }
    }

    public int top() {
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1);
    }

    public int getMin() {
        if (list.size() == 0) {
            return 0;
        }
        Integer min = treeMap.firstKey();
        return min;
    }
}
