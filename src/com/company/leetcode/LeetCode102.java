package com.company.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 102 二叉树按层收集结点
 */
public class LeetCode102 {
    public static void main(String[] args) {

    }

    public static  List<List<Integer>> levelOrderBottom(TreeNode root){
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            ans.add(curAns);
        }
        return ans;
    }
}
