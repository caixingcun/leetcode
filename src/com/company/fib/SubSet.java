package com.company.fib;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集 迭代/递归
 */
public class SubSet {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3};
        System.out.println(new SubSet().subsets(arr));
    }

    /**
     * Given [1,2,3]
     * Return [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 路径规划
     * 每个元素 是否在子集中 构成所有组合
     *
     * 路径规划
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        dfs(nums, result, temp, 0);
        return result;
    }


    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> temp, int cur) {
        if (nums.length == cur) { //暂存数据达到目标值
            result.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[cur]); //加入当前元素
        dfs(nums, result, temp, cur + 1); // 下一个元素
        temp.remove(temp.size() - 1); //回溯
        dfs(nums, result, temp, cur + 1); // 忽略当前元素 直接下一个元素
    }

}
