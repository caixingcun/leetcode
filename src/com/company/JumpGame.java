package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跳跃游戏
 * 初始位置为 index=0
 * Given  2 3 1 1 4
 * <p>
 * Return 2
 * <p>
 * 实际路径 3 4
 * <p>
 * <p>
 * 3      1      step1
 * 1 1 4    1      step2
 * 14 4      4
 * 4
 * <p>
 * 思路：动态规划/路径规划/回溯
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 9, 6, 5, 7, 0, 7, 2, 7, 9, 3, 2, 2, 5, 7, 8, 1, 6, 6, 6, 3, 5, 2, 2, 6, 3};
        int jump = new JumpGame().jump(arr);
        System.out.println(jump);
    }

    int memory_path_size;
    List<Integer> memory_path = new ArrayList<>();

    public int jump(int[] nums) {
        memory_path_size = nums.length;
        //状态数组
        List<Integer> result = new ArrayList<>(); //所有的path
        List<Integer> path = new ArrayList<>(); //单path所有 数字
        dfs(nums, result, path, 0);

        int min_step = nums.length;
        for (int i = 0; i < result.size(); i++) {
            Integer pathSize = result.get(i);
            min_step = Math.min(min_step, pathSize);
        }
        return min_step;
    }

    private void dfs(int[] nums, List<Integer> result, List<Integer> path, int index) {
        //出口
        //已经到最后一步了
        if (index >= nums.length - 1) {
            result.add(path.size());
            Math.min(memory_path_size, path.size());
            return;
        }

        int accessStep = nums[index]; //能跳跃的步数
        for (int i = 1; i <= accessStep; i++) {
            if (index + i >= nums.length || path.size() > memory_path_size) continue; //超出界限 跳的次数太长 直接跳过
            path.add(index + i);
            dfs(nums, result, path, index + i);
            path.remove(path.size() - 1);
        }

    }


}
