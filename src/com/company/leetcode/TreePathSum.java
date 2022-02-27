package com.company.leetcode;

/**
 * 二叉树路径和为x
 */
public class TreePathSum {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);

        hasSum = false;
        if (node == null) {
            hasSum = false;
        } else {
            process(node, 0, 3);
        }

        System.out.println(hasSum);
    }

    public static boolean hasSum = false;

    public static void process(TreeNode root, int preSum, int sum) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == sum) {
                hasSum = true;
            }
            return;
        }
        if (root.left != null) {
            process(root.left, preSum + root.val, sum);
        }
        if (root.right != null) {
            process(root.right, preSum + root.val, sum);
        }
    }
}
