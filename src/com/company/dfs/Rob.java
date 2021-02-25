package com.company.dfs;

public class Rob {
    /**
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        //  1  2  3  1
        //  1                    dp[0] =1
        //     2>1               dp[1] =2
        //        3>2 dp[i-2]+ai dp[2] =dp[0] +3 = 4
        //                       dp[3] =

        // 不能相邻
        // 动态规划
        // dfs

        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        int len = nums.length;
        int dp[] = new int[len];  //偷到第i间屋子 能偷取到的最多金额
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        int result = 0;
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;

    }
}
