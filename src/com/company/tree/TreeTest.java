package com.company.tree;

import java.util.*;

public class TreeTest {


    public static void main(String[] args) {


    }

    class Solution {
        // 前序 存hash 方便快速取出数据 中序 位置，便于通过中序位置 获取中序左右子树长度，通过获取的左右子树长度,分割之前的中序 ，以及切割出子数前序数列 ，进行迭代


        private Map<Integer, Integer> indexMap;

        public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            //当前树节点构建
            //出口
            if (preorder_left > preorder_right) {
                return null;
            }
            // root 在前序中的位置
            int preorder_root = preorder_left; //前序第一个数据 就是根节点
            // 根据数据 获取中序 root 的索引
            int inorder_root = indexMap.get(preorder[preorder_root]);

            // 构建当前节点
            TreeNode root = new TreeNode(preorder[preorder_root]);

            //  当前节点在中序中的位置 - 中序最左侧节点  = 左子树节点数
            int size_left_subtree = inorder_root - inorder_left;
            // 前序左子树 left+1 扣除当前root ， left + left_size = 前序左子树 最大index
            // 前序右子树 left+left_size+1 左子树后一位作为右子树第一位   pre_right 之前的前序右子树右侧

            // 中序左子树 inorder_left , inorder_root-1  中序的左子树，介于 当前节点之前
            // 中序右子树 inorder_root+1 , inorder_right
            root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
            // 递归地构造右子树，并连接到根节点
            // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
            root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
            return root;
        }

        public TreeNode buildTree(int[] preorder, int[] inorder) {

            int n = preorder.length;
            indexMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                //快速定位跟节点 在前序中的位置
                indexMap.put(inorder[i], i);
            }

            return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        }
    }


    /**
     * 根据二叉树 前序 中序 构造二叉树
     * 前序 先节点 后左右子叶
     * 中序 先左子叶 后节点 再右子叶
     * Given  前序遍历 preorder = [3,9,20,15,7]
     * <p>
     * 中序遍历 inorder  = [9,3,15,20,7]
     * <p>
     * Hash 存储 中序 数据 跟其index
     * 调用递归 构建节点
     * <p>
     * 递归方法
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 思路
     *
     * @param preorder
     * @param inorder
     * @return
     */
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>(); //存储 节点数据 与中序位置
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }

        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        //出口
        //前序左右节点相遇
        if (preorder_left > preorder_right) {
            return null;
        }

        //pre   R x x
        //in    x R x

        //前序第一个节点 就是跟节点， 中序 也就是 Hash 可以取出其中序位置 ，
        // 构建当前节点 前序的第一个节点值（还缺左右子节点）
        // 根据节点中序位置可以拆分左右子树，还是中序子树




        // 前序 左子节点 作为root
        int preorder_root = preorder_left;
        //获取中序中当前节点数 所在位置
        int inorder_root = indexMap.get(preorder[preorder_root]);
        //构建当前根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);

        //得到左子树的节点数
        int size_left_subtree = inorder_root - inorder_left;
        //递归构造左子树，并连接到根节点
        //先遍历中 [从 左边界+1开始的 size_left_subtree] 个元素 就对应了中序遍历中 [从左边界开始 到根节点定位-1]的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_right + size_left_subtree, inorder_left, inorder_root - 1);
        //递归构造右子树 并连接到根节点
        //前序遍历中 [从 左边界+1+ 左子树节点数,右边界] 元素中对应 中序[从根节点+1到 右边界]元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);

        return root;
    }

    /**
     * 二叉树最大深度  层序遍
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result.size();
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                temp.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            result.add(temp);
        }

        return result.size();
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
     * <p>
     * 二叉搜索树 特质
     * 左子树只包含小于当前节点的数
     * 右字数只包含大于当前节点的数
     * 左子树和右子树自身必须是二叉搜索树
     */

    public boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfsBst(result, root);
        if (result.size() <= 1) {
            return true;
        }

        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) < result.get(i + 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;

    }

    private void dfsBst(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        dfsBst(result, root.left);
        result.add(root.val);
        dfsBst(result, root.right);
    }

    /**
     * 二叉搜索数
     * 1..n 为节点 共有多少种儿叉搜索树
     * <p>
     * 1个节点 搜索树 为 1
     * <p>
     * 选择第i作为根
     * 第i所有二叉搜索树 集合是左子树集合 和 右子数集合的 笛卡尔积
     * 对于笛卡尔积中的每个元素 加上根节点后形成完成的二搜索树
     * <p>
     * G(n) =  i从1..n 求和 G(i-1)*G(n-i)
     *
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
