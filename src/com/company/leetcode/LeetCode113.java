package com.company.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 113 路径总和2
 */
public class LeetCode113 {

    public static void main(String[] args) {

    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) {
            return result;
        }
        process(result, new LinkedList<>(), root, 0, targetSum);

        return result;
    }

    private static void process(List<List<Integer>> result, LinkedList<Integer> targetList, TreeNode root, int tempSum, int targetSum) {

        targetList.addLast(root.val);
        tempSum += root.val;
        //最终节点
        if (root.left == null && root.right == null && targetSum == tempSum) {
            result.add(copy(targetList));
            return;
        }

        if (root.left != null) {
            process(result, targetList, root.left, tempSum, targetSum);
            targetList.removeLast();
        }
        if (root.right != null) {
            process(result, targetList, root.right, tempSum, targetSum);
            targetList.removeLast();
        }

    }

    private static List<Integer> copy(LinkedList<Integer> targetList) {
        List<Integer> temp = new ArrayList();
        for (Integer integer : targetList) {
            temp.add(integer);
        }
        return temp;
    }


}
