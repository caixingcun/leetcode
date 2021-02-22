package com.company.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTest {


    public static void main(String[] args) {


    }

    /**
     * 二叉树 层序遍历
     */


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }

        return result;

    }



    /**
     * 判断是否是二叉搜索树
     *
     * 二叉搜索树 特质
     * 左子树只包含小于当前节点的数
     * 右字数只包含大于当前节点的数
     * 左子树和右子树自身必须是二叉搜索树
     */

    public boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfsBst(result,root);
        if (result.size() <= 1) {
            return true;
        }

        for (int i = 0; i < result.size()-1; i++) {
            if (result.get(i) < result.get(i + 1)) {
                continue;
            }else{
                return false;
            }
        }
        return true;

    }

    private void dfsBst(List<Integer> result ,TreeNode root) {
        if (root == null) {
            return ;
        }
        dfsBst(result,root.left);
        result.add(root.val);
        dfsBst(result,root.right);
    }

    /**
     * 二叉搜索数
     * 1..n 为节点 共有多少种儿叉搜索树
     *
     *  1个节点 搜索树 为 1
     *
     *  选择第i作为根
     *  第i所有二叉搜索树 集合是左子树集合 和 右子数集合的 笛卡尔积
     *  对于笛卡尔积中的每个元素 加上根节点后形成完成的二搜索树
     *
     * G(n) =  i从1..n 求和 G(i-1)*G(n-i)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // 根节点  其余的 n-1 个
        int[] dp = new int[n + 1]; //存储每一个节点 对应二叉搜索数 数量

        dp[0] = 1; // n =0 root =null  搜索树为1
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = dp[i] + dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];

    }

    /**
     * 二叉树中序遍历   先左叶子节点 再根节点 再右叶子节点
     * DFS 深度  回溯
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        dfs(result, root);
        return result;
    }

    private void dfs(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(result, root.left);
        result.add(root.val);
        dfs(result, root.right);

    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
