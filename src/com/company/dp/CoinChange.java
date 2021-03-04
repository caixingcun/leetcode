package com.company.dp;

import com.company.stack.MinStack;

import java.util.*;

public class CoinChange {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 5};
        int[] arr = {1, 2147483647};
        System.out.println(new CoinChange().coinChange(arr, 2));
    }


    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1]; //全1元
        Arrays.fill(dp, max);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }



}
