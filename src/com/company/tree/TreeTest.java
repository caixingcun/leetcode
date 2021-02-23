package com.company.tree;

import javax.xml.soap.Node;
import java.util.*;

public class TreeTest {

    public static void main(String[] args) {
        int[] postorder = {9, 15, 7, 20, 3};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode node = new TreeTest().buildTree2(inorder, postorder);




    }

    private static void print(TreeNode node) {

        if (node == null) {
            System.out.println("null");
            return;
        }
        if (node.left != null) {
            print(node.left);
        }
        if (node.right != null) {
            print(node.right);
        }
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {

        int n = inorder.length;
        inOrderMap = new HashMap<>(); //存储 节点数据 与中序位置
        for (int i = 0; i < n; i++) {
            inOrderMap.put(inorder[i], i);
        }
        return myBuildTree2(postorder, inorder, 0, n - 1, 0, n - 1);
    }

    /**
     * * 根据 中序 后序 遍历数列 生成二叉树
     * *   2
     * * 1  3 中序
     * *   3
     * * 1  2 后续 先右子树 后左子树  再节点
     * * <p>
     * * 基本同 105  前序 中序 遍历数列 生成二叉树
     * 取后续最后一个元素 作为节点
     *
     * @param postOrder 后续
     * @param inOrder   中序
     * @param from      后续 左
     * @param to        后续右
     * @param left      中序 左
     * @param right     中序右
     * @return
     */
    private TreeNode myBuildTree2(int[] postOrder, int[] inOrder, int from, int to, int left, int right) {

        if (left > right) {
            return null;
        }
        int root_value = postOrder[to];


        int root_in_index = inOrderMap.get(root_value);
        TreeNode root = new TreeNode(root_value);

        int left_len = root_in_index - left;

        root.left = myBuildTree2(postOrder, inOrder, from, from + left_len - 1, left, root_in_index - 1);

        root.right = myBuildTree2(postOrder, inOrder, from + left_len, to - 1, root_in_index + 1, right);

        return root;
    }


    private Map<Integer, Integer> inOrderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        inOrderMap = new HashMap<>(); //存储 节点数据 与中序位置
        for (int i = 0; i < n; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


    /**
     * * 根据二叉树 前序 中序 构造二叉树
     * * 前序 先节点 后左右子叶
     * * 中序 先左子叶 后节点 再右子叶
     * * Given  前序遍历 preorder = [3,9,20,15,7]
     * *         中序遍历 inorder  = [9,3,15,20,7]
     * *Return        3
     * *           9    20
     * *              15   7
     * *
     * * 递归  给出条件  前序  from  to   （0- n-1）
     * *               中序  left right （0- n-1）
     * *  一颗树  前序 的左右子树节点数 与 中序 左右子树节点数相同
     *
     * @param preOrder
     * @param inOrder
     * @param from
     * @param to
     * @param left
     * @param right
     * @return
     */
    private TreeNode myBuildTree(int[] preOrder, int[] inOrder, int from, int to, int left, int right) {

        int root_value = preOrder[from];
        int root_in_index = inOrderMap.get(root_value);
        TreeNode root = new TreeNode(root_value);
        int left_len = root_in_index - left;

        root.left = myBuildTree(preOrder, inOrder, from + 1, from + 1 + left_len, left, root_in_index - 1);
        root.right = myBuildTree(preOrder, inOrder, from + 1 + left_len + 1, to, root_in_index + 1, right);

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
