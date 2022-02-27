package com.company.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 105 已知二叉树的前序中序数组，请重建这个树
 */
public class LeetCode105 {
   static   Map<Integer, Integer> cacheMap = new HashMap<>();

    public static void main(String[] args) {
        int[] firstArr = {1, 2, 4, 5, 3, 6, 7};
        int[] middleArr = {4, 2, 5, 1, 6, 3, 7};

        for (int i = 0; i < middleArr.length; i++) {
            cacheMap.put(middleArr[i], i);
        }
        TreeNode root = f(firstArr, 0, 6, middleArr, 0, 6);

    }


    public static final TreeNode f(int[] firstArr, int firstStart, int firstEnd, int[] middleArr, int middleStart, int middleEnd) {
        if (firstArr == null) {
            return null;
        }

        //中序根节点赋值
        TreeNode root = new TreeNode(firstArr[firstStart]);
        //中序第一个数 位置
        int middleIndex = cacheMap.get(root.val);
        int leftCount = middleIndex - middleStart;
        int rightCount = middleEnd - middleIndex;

        if (leftCount > 0) {
            root.left = f(firstArr, firstStart + 1, firstStart + 1 + leftCount - 1, middleArr, middleStart, middleIndex - 1);
        }

        if (rightCount > 0) {
            root.right = f(firstArr, firstEnd - rightCount + 1, firstEnd, middleArr, middleEnd - rightCount + 1, middleEnd);
        }

        return root;
    }

}
