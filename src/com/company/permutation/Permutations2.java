package com.company.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列路径
 * Given  [1,2,3]
 * Return 长度为3的所有数组排列情况 可以重复
 * 1           2           3
 * 1   2   3   1   2   3   1   2   3
 * 123 123 123 123 123 123 123 123 123
 * 路径规划
 */
public class Permutations2 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> permute = new Permutations2().permute(arr);
        System.out.println(permute);
    }

    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, path, res);
        return res;
    }

    private void dfs(int[] nums, List<Integer> path, List<List<Integer>> res) {
        //出口
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            dfs(nums, path, res);
            path.remove(path.size() - 1);
        }
    }

}
