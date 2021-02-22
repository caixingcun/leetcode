package com.company.stack;

import java.util.Stack;

public class RectangleArea {

    public static void main(String[] args) {
//        int[] arr = {2, 1, 5, 6, 2, 3};
        int[] arr = {1};
        System.out.println(new RectangleArea().largestRectangleArea(arr));
    }

    /**
     * 最大矩形面积   84
     * Given 2 1 5 6 2 3
     * Return 10     5x2
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        //暴力破解
        //遍历每一个柱状高度 依次向两侧扩展，直到高度低于选中柱状高度
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(getArea(heights, i), maxArea);
        }
        return maxArea;
    }

    private int getArea(int[] heights, int i) {
        int height = heights[i];
        int left = i;
        while (left - 1 >= 0 && heights[left - 1] >= height) {
            left--;
        }
        int right = i;
        while (right + 1 < heights.length && heights[right + 1] >= height) {
            right++;
        }

        return (right - left + 1) * height;
    }

    /**
     * 矩形面积 优化 栈
     * <p>
     * 遍历每一个柱子高度
     * *
     * * *
     * * *
     * 第二根比第一根小 那么第一根为高度向两侧扩散  面积为 （1-0）*height[0]
     * <p>
     * *   *
     * * * *
     * * * *
     * 第三根比第二根大 则不能确定以第二根柱子为高度 向两侧扩散的宽度
     * <p>
     * *   *
     * * * *
     * * * * *
     * 第四根 比第三根小 能确定以第三根为高度的宽度 为 (3-2)*height[2]
     * -    能确定宽度的前一根不能确定宽度的位置  即 第二根
     * 此时  比第二根小 能确定以第二根为高度的快读 为 （3-0）*height[1]
     * _    能确定款第的前一根不能确定宽度的位置 即 第一根
     * <p>
     * <p>
     * <p>
     * <p>
     * 遍历  当前高度 > 之前的高度最大值
     * 当前高度更新到高度最大值 当前index 更新到高度最大子index
     * 面积由当前index 向右计算得出
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {//上一跟比当前的高 能确定以上一根为面积的右侧为 index 为 i
                stack.pop();//将上一根出栈
            } //循环出stack中所有能确定宽度的 index
            left[i] = stack.isEmpty() ? -1 : stack.peek(); //如果栈空了 左侧 第i根能确定的面积 左侧 为 -1，否则就是 栈内 比当前i 高度还低的位置
            stack.push(i);
        }

        stack.clear();
        //从右向左 再执行一次 可以确定每个index right 所在位置
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int result = 0;
        for (int i = 0; i < left.length; i++) {
            result = Math.max(result, (right[i] - left[i]-1) * heights[i]);
        }
        return result;
    }
}
