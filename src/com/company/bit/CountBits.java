package com.company.bit;

import java.util.Arrays;

public class CountBits {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountBits().countBits(2)));
    }

    /**
     * 计算输出 从 0 -num 中每个数 转换为 二进制数 中 1的个数
     * 方案1 暴力破解
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            String s = Integer.toBinaryString(i);
            int sumOne = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    sumOne++;
                }
            }
            result[i] = sumOne;
        }
        return result;
    }

    /**
     * 方案2  动态规划
     *
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        // dp[i]  第i个数 中1的个数
        // dp[0] = 0
        // dp[1] = 1
        // dp[2] = 1
        // dp[3] = 2
        // dp[4] = 1
        // dp[5] = 2
        // dp[6] = 2
        // dp[7] = 3
        // dp[8] = 1
        // dp[9] = 2

        //奇数  = 前面一个dp[i-1] + 1
        //偶数  = dp[i/2]


        int[] dp = new int[num + 1];
        dp[0] = 0;
        if (num == 0) {
           return dp;
        }
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            if (i % 2 == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i / 2];
            }
        }
        return dp;
    }
}
