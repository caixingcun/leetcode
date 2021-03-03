package com.company.dfs;

import sun.nio.cs.ext.MacThai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaxProfit {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 0, 2};

//        System.out.println(new MaxProfit().maxProfit(arr));
        System.out.println(new MaxProfit().maxProfit2(arr));
    }

    public int maxProfit(int[] prices) {
        //类温度
        //贪心 堆栈
        //低价买入 高价卖出
        // 买卖冷冻  3天
        // dfs 暴力破解
        // 1    2     3     0     2
        //买    卖
        List<Integer> result = new ArrayList<>();

        dfs(prices, 0, result, 0, 1);

        Collections.sort(result);

        return result.get(result.size() - 1);
    }

    /**
     * @param prices
     * @param day    第0- n-1 天
     * @param result
     * @param sum
     * @param state  状态  可买 1  ， 可卖 2  冻结 3
     */
    private void dfs(int[] prices, int day, List<Integer> result, int sum, int state) {
        if (day == prices.length) {
            result.add(sum);
            return;
        }
        if (state == 3) { //冻结
            dfs(prices, day + 1, result, sum, 1);//不操作
        }
        if (state == 2) { //可卖
            dfs(prices, day + 1, result, sum + prices[day], 3); //卖出
            dfs(prices, day + 1, result, sum, 2); //不操作
        }
        if (state == 1) { //可买
            dfs(prices, day + 1, result, sum - prices[day], 2);//买入
            dfs(prices, day + 1, result, sum, 1);//不操作
        }
    }

    /**
     * 动态转移
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int day = prices.length;
        // 动态规划  三种状态 每天的资金持有数
        int[][] dp = new int[3][day];
        // 0  //持有股票
        // 1  //不持有 冻结
        // 2  //不持有 不冻结
        dp[0][0] = -prices[0]; //第一天没有差价赚
        dp[1][0] = 0; //实际不存在
        dp[2][0] = 0; //不持有 不冻结

        for (int i = 1; i < day; i++) {
            //i天持有
            // = i-1天本就持有
            // = i-1天 不持有不冻结 i天买入
            dp[0][i] = Math.max(dp[0][i - 1], dp[2][i - 1] - prices[i]);
            //i天冻结
            //= 前一天必然是卖出动作 前一天肯定要持有
            dp[1][i] = dp[0][i - 1] + prices[i];
            //i天 不持有不冻结
            // =  前一天不持有 冻结 或者前一天不持有不冻结
            dp[2][i] = Math.max(dp[1][i - 1], dp[2][i - 1]);
        }

        return max(dp[0][day - 1], dp[1][day - 1], dp[2][day - 1]);

    }

    public int max(int... prices) {
        int max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i]);
        }
        return max;
    }

}
