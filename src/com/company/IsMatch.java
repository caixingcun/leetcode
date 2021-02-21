package com.company;

public class IsMatch {

    /**
     * 44 通配符匹配
     *  * 匹配任意长度任意字符
     *  . 匹配 单个字符
     *  思想：动态规划
     *  1.定义状态数组
     *  2.初始化状态数组
     *  3.状态转移方程
     *  4.返回结果
     *
     *   空 a b c d
     * 空 T F F F F
     * a F T F F F
     * b F F T F F
     * * F F T T T
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

                //  1.定义状态数组
                //  2.初始化状态数组
                //  3.状态转移方程
                //  4.返回结果
        int m = s.length();
        int n = p.length();
        //1 定义状态方程 多补了首位的空格
        boolean[][] dp = new boolean[m+1][n+1]; //用来存储 从0-len 各个长度是否匹配

        //2 初始化状态数组 补了空格
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            //第一列匹配 通配符 对空格 ,如果遭遇 * 则看上一格是否匹配上
            // 情况1 *  能匹配空格
            // 情况2 xxx xx*  前面的xxx 能匹配上 xx ；*会被当成0个字符
            dp[0][i] = dp[0][i - 1] && p.charAt(i) == '*';
        }
        //3.状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s.charAt(i-1);
                char p1 = p.charAt(j-1);
                if (c1 == p1 || p1 == '.') { //遍历到的两个 字符匹配
                    dp[i][j] = dp[i - 1][j - 1]; //那么前一位匹配 这里就匹配
                } else if (p1 == '*') { //最新通配符字符是 *
                    dp[i][j] = dp[i][j - 1]||dp[i-1][j];
                    //要么当前s与之前的通配符串匹配 *表示 空
                    //要么之前的s与当前的通配符串匹配 *表示之前s的最后一位
                }
            }
        }
        //返回结果
        return dp[m][n];
    }
}
