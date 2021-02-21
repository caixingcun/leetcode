package com.company.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列路径
 * Given  [1,2,3]
 * Return 长度为3的所有数组排列情况 可以重复
 * 包含重复数据nums
 * 112
 * 返回 返回不重复全排列
 * 路径规划
 */
public class Permutations3 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        List<List<Integer>> permute = new Permutations3().permute(arr);
        System.out.println(permute);
    }
    //定义一个新的变量数据 来赋值 表示某一位已经被使用过
    boolean[] vis;
    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums); //规避 3303 类型 剪枝不彻底
        dfs(nums, path, res, 0);
        return res;
    }

    private void dfs(int[] nums, List<Integer> path, List<List<Integer>> res, int pos) {
        //出口
        if (pos == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 剪枝
            // 当前i被之前主枝干使用过 continue
            // 当前 数据与上一个数据一致，并且上一个数据被使用过 i>0用来保证i-1不越界
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && vis[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            vis[i] = true;  //使用后 复制为true
            dfs(nums, path, res, pos + 1);
            vis[i] = false; //回溯前再修改过来
            path.remove(path.size() - 1);
        }

    }

}
