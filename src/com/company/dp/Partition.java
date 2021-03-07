package com.company.dp;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class Partition {

    public static void main(String[] args) {
        System.out.println(new Partition().partition("abbab"));
    }

    boolean[][] dp;
    List<List<String>> res = new ArrayList<>();
    List<String> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        //动态规划
        // dp[i][j+1] = dp[i][j]+s.charAt(j+1)==s.chatAt(i);

        int len = s.length();
        dp = new boolean[len][len];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], true);
        }

        for (int j = 1; j <= len; j++) { //长度
            for (int i = 0; i < len; i++) { //起始位置
                if (j > len - i) {
                   continue;
                }
                int x = i;
                int y = i + j - 1;
                if (x == y) {
                    dp[x][y] = true;
                } else if (x == y - 1) {
                    dp[x][y] = (s.charAt(x) == s.charAt(y));
                } else {
                    dp[x][y] = (s.charAt(x) == s.charAt(y) && dp[x + 1][y - 1]);
                }

            }
        }

        System.out.println(dp[2][3]);
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int i) {
        if (i == s.length()) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) {
                String substring = s.substring(i, j + 1);
                ans.add(substring);
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

}
