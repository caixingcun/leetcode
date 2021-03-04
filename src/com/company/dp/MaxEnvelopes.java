package com.company.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354 俄罗斯套娃信封问题
 */
public class MaxEnvelopes {

    public static void main(String[] args) {
        int[][] arr = {{5, 4}, {6, 4}, {6, 7},{6, 1}, {2, 3}};
        new MaxEnvelopes().maxEnvelopes(arr);


    }

    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }else{
                return o2[1] - o1[1];
            }
        });
        int len = envelopes.length;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(dp[i], ans);
            }
        }
        //同样大小的宽度只能放一个
        return ans;
    }
}
