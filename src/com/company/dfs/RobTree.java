package com.company.dfs;

import com.company.tree.TreeNode;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobTree {


    public int rob(TreeNode root) {

        int[] result = robInternal(root);

        return Math.max(result[0], result[1]);
    }

    private int[] robInternal(TreeNode root) {
        int[] result = new int[2];
        if (root != null) {
            int[] left = robInternal(root.left);
            int[] right = robInternal(root.right);
            // 0表示不偷当前节点 最大值为 左右节点，偷与不偷最大值之和
            result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            // 1表示偷当前节点，左右节点都只能取0 也就是不偷的
            result[1] = left[0] + right[0] + root.val;
        }
        return result;
    }


}
