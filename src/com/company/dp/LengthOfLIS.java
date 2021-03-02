package com.company.dp;

/**
 * 300 最长递增子序列
 * 动态规划 dp[i]  = max(dp[j]) + 1   num[j] <num[i]
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        //动态规划

        int length = nums.length;
        //定义状态数组
        int[] dp = new int[length];
        //初始化状态
        dp[0] = 1;
        int maxans = 1;
        //状态方程
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(dp[i], maxans);
        }
        return maxans;
    }

}
