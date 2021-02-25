package com.company.dp;

public class NumTest {

    /**
     * 最大子数组 乘机最大
     * 动态规划
     * 当前位置是负数 需要前一个位置向前X位 乘积为负数的最小数
     * 当前位置是正数 需要匹配前一个位置向前X为 乘机为正数的最大值
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // 连续 子数组
        // 动态规划  滑动窗口 //贪心算法
        // 连续子数组乘积 最大   负数个数为偶数 ，不能包含0

        // 当前 ai 为正数
        //  dp[i]+ = dp[i-1]+ * ai
        //  dp[i]- = dp[i-1]- * ai
        //当前 ai 为负数
        // dp[i]+ = dp[i-1]- * ai
        // dp[i]- = dp[i-1]+ * ai

        // max = max{di[i]+}


        int maxF = nums[0];
        int minF = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //放 num[i] 为了防止 从0开始的数组
            int mx = maxF;
            int mn = minF;
            maxF =max(mx * nums[i], mn * nums[i], nums[i]);
            minF = min(mx * nums[i], mn * nums[i], nums[i]);
            result = max(maxF, result);
        }
        return result;
    }

    private int max( int... arr) {
        int max = arr[0];
        for (int i = 1; i <arr.length ; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    private int min( int... arr) {
        int min = arr[0];
        for (int i = 1; i <arr.length ; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

}
