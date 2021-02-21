package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递归相关
 */
public class RecursionTest {
    public static void main(String[] args) {
        //组合总和
//        int[] arr = {2, 3, 6, 7};
//        System.out.println(combinationSum(arr, 7));

        //组合总和2
//        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        int[] arr = {2,5,2,1,2};
        System.out.println(combinationSum2(arr, 5));
    }

    /**
     * 组合总数
     * 在 candidates 中找出总数 = target 的所有组合
     * 数据可以重复利用
     *
     * @param candidates
     * @param target
     * @return Given [2,3,6,7]
     * Target 7
     * <p>
     * 递归思想
     * pos 从 0 - len-1   需要一个pos  需要 candidates
     * 每次进递归函数  根据pos会拿到一个数字 ，递归 路径分裂 组合追加下一个数据/ 添加当前数据到 集合
     * 需要一个 二维集合 用来存结果
     * 需要一个 集合 用来存 每一个组合
     * <p>
     * 出口
     * pos ==len
     * <p>
     * 需要求和后 求与K的差
     * 需要引入 target
     * <p>
     * 回溯
     * 从组合中移除 添加的数据
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        dfs(candidates, result, temp, 0, target);

        return result;
    }

    public static void dfs(int[] candidates, List<List<Integer>> result, List<Integer> temp, int pos, int target) {
        //出口
        if (pos == candidates.length) {
            return;
        }
        //满足条件的出口
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        //直接跳过当前pos  产生分支
        dfs(candidates, result, temp, pos + 1, target);

        // 当前pos
        if (candidates[pos] <= target) {
            temp.add(candidates[pos]);//添加当前位置值到 集合
            dfs(candidates, result, temp, pos, target - candidates[pos]);
            temp.remove(temp.size() - 1); //回溯   不能让
        }

    }

    /**
     * 组合总数 只能使用一次
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> template = new ArrayList<>();
        Arrays.sort(candidates);
        System.out.println(Arrays.toString(candidates));
        dsf2(candidates, result, template, 0, target);

        List<List<Integer>> newResult = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            if (!newResult.contains(result.get(i))) {
            newResult.add(result.get(i));
            }
        }
        return newResult;
    }

    private static void dsf2(int[] candidates, List<List<Integer>> result, List<Integer> template, int pos, int target) {

        if (target == 0) {
            result.add(new ArrayList<>(template));
            return;
        }
        if (pos == candidates.length) {
            return;
        }
        //直接跳过 动作忽略 不算当前pos  进行下一种情况分支
        dsf2(candidates, result, template, pos + 1, target);

        // 判断当前pos 加进来 进行下一种情况
        if (candidates[pos] <= target) {
            template.add(candidates[pos]);
            dsf2(candidates, result, template, pos + 1, target - candidates[pos]);
            template.remove(template.size() - 1);
        }
        //直接超出的 不会计入 result
    }

}
