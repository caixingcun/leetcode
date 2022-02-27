package com.company.leetcode;

/**
 * 判断是二叉搜索树
 * 搜索 1..N
 */
public class LeetCode98 {
    public static void main(String[] args) {

    }


    public  static class Info{
        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
        public int max;
        public int min;
        public boolean isBST;
    }


   public static  Info process(TreeNode root) {
       if (root == null) {
           return null;
       }

       Info leftInfo = process(root.left);
       Info rightInfo = process(root.right);
       int max = root.val;
       int min = root.val;

       if (leftInfo != null) {
           max = Math.max(leftInfo.max, max);
           min = Math.min(leftInfo.min, min);
       }
       if (rightInfo != null) {
           max = Math.min(rightInfo.max, max);
           min = Math.min(rightInfo.min, min);
       }
       boolean isBST = true;

       if (leftInfo != null && !leftInfo.isBST) {
           isBST = false;
       }
       if (rightInfo != null && !rightInfo.isBST) {
           isBST = false;
       }

       boolean leftMaxLessX = leftInfo == null? true : (leftInfo.max < root.val);

       boolean rightMinMoreX = rightInfo == null ? true: (rightInfo.min > root.val);

       if (!leftMaxLessX || !rightMinMoreX) {
           isBST = false;
       }

       return new Info(max, min, isBST);
   }
}
