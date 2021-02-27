package com.company.stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(new DailyTemperatures().dailyTemperatures(arr)));
    }

    public int[] dailyTemperatures(int[] T) {
        // 气温列表
        // 列表日期内 之后下一个高温位置距离

        //  T[i]<T[i]+1   result[i] = 1
        // T[i] >=T[i]+1  result[i] 待定  压入堆栈
        // 每次出站 对比是否 T[stackT] < T[i] , result[stack] = i-stackT
        int len = T.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int preHighIndex = stack.pop();
                result[preHighIndex] = i - preHighIndex;
            }
            stack.push(i);
        }
        //如果一路向下 那都是 0
        while (!stack.isEmpty()) {
            int preHighIndex = stack.pop();
            result[preHighIndex] = 0;
        }
        return result;
    }
}
