package com.company.dp;

public class CountSubstrings {
    public static void main(String[] args) {
        System.out.println(new CountSubstrings().countSubstrings("aaa"));
    }
    /**
     * 647 计算所有 回文数 子串数量
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        boolean dp[][] = new boolean[length][length];
        int count = 0;
        for (int i = 1; i <= s.length(); i++) { //长度
            for (int j = 0; j < s.length() - i + 1; j++) {
                int x = j;
                int y = j + i - 1;
                if (x == y) {
                    dp[x][y] = true;
                    count++;
                } else if (x + 1 == y) {
                    dp[x][y] = (s.charAt(x) == s.charAt(y));
                    if (dp[x][y]) {
                        count++;
                    }
                } else {
                    dp[x][y] = (s.charAt(x) == s.charAt(y) && dp[x + 1][y - 1]);
                    if (dp[x][y]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
