package com.company.dp;

import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 动态规划
 * 字符串操作相关
 */
public class Word {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("aa");
//        list.add("aaa");
//        list.add("aaaa");
//        list.add("aaaaa");
//        list.add("aaaaaa");
//        list.add("aaaaaaa");
//        list.add("aaaaaaaa");
//        list.add("aaaaaaaaa");
//        list.add("aaaaaaaaaa");
//        System.out.println(new Word().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", list));
        list.add("leet");
        list.add("code");
        System.out.println(new Word().wordBreak("leetcode", list));

    }

    /**
     * 139 单词拆分 动态规划
     *
     * 状态转移方程 dp[i] = dp[j]&&check[j,i]
     * 第0-i 是否匹配  dp[<i]&& check(<i,i)
     * i从 1 - len
     * j<i
     * s从0到底i个数 组成的串 是否匹配 看 0-i中的任意一个dp[<i]=true 并且 <i,j 在匹配数组中存在
     */
    public boolean wordBreak2(String s, List<String> wordDict) {

        Set<String> hashSet = new HashSet<>(wordDict);

        // 状态数组
        int length = s.length();
        boolean[] dp = new boolean[length + 1]; //用来存储 s中 0- i 是否被完整切割
        //初始化状态方程
        dp[0] = true; // 默认0 是空 是匹配的

        //双指针
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {

                if (dp[j] && hashSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * s 能被完整拆分为 wordDict中一个或者多个
     * 深度搜索 记忆化回溯
     *
     * @param s
     * @param wordDict
     * @return
     */
    /**
     * s 能被完整拆分为 wordDict中一个或者多个
     * 动态规划/深度搜索
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // s 被完整拆成几个 wordDict中的单词

        return dfs(wordDict, s);
    }

    private boolean dfs(List<String> wordDict, String s) {
        if (wordDict.contains(s)) {
            return true;
        }

        for (int i = 0; i < wordDict.size(); i++) {
            if (s.startsWith(wordDict.get(i))) {
                boolean ok = dfs(wordDict, s.substring(wordDict.get(i).length()));
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 编辑距离
     * Given word1 word2
     * Return  操作最小次数后 word1=word2
     * 可以进行 修改/添加/删除操作
     * <p>
     * 对 word1 的 插入/删除/修改 等同于 对word2 的 删除/插入/修改
     * 调整为 三种操作
     * 对word1 插入
     * 对word2 插入
     * 对word1 修改
     * <p>
     * 在A中插入一个字符
     * A        horse
     * B        ro    ros
     * 编辑距离   a     a+1
     * 假设 horse -> ro 需要a次编辑
     * 则   horse -> ros 需要<=a+1 次编辑
     * <p>
     * 在B中插入一个字符
     * A        hors
     * B        ro      ros
     * 假设      b次编辑  <=b+1次编辑
     * <p>
     * 修改一个A字符
     * A        hors
     * B        ro      ros
     * 假设      c次     《=c+1
     * <p>
     * <p>
     * 从 horse 变成 ros 编辑距离 min(a+1,b+1,c+1)
     * <p>
     * 继续拆解
     * A 为空   A从空转换为 ro  需要2次编辑
     * B 为空    B从空转换为horse 需要5次编辑
     * 动态规划解决该问题
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        //1.定义状态方程
        // horse  word1（1-i）
        // ros    word2）(1..j)
        //存储 word1 - word2 从头到各个位置子串需要的编辑次数
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m][n]; //dp[i][j] A的前i个 B的前j个 字母 编辑距离

        //2.初始化状态方程 0算空格
        //边界初始化  从 1-n  需要i次编辑(空字符串需要0次)
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = j;
        }
        //状态转移方程
        // A 的 前 i-1 个字符 与 B的前 j-1 个字符 编辑次数为 dp[i-1][j-1]
        // 如果 A的i个字符 与B的j 个字符不同 至少需要 dp[i][j] <= dp[i-1][j-1] + 1
        //                        相同  dp[i][j] <=dp[i-1][j-1]
        // A的 前i-1个字符与B的前j个字符 编辑次数 dp[i-1][j]
        // A的第i个字符，可在B后加一个同A一样的i个字符 只需额外编辑一次 dp[i][j]<= dp[i-1][j] + 1
        // 同理可得   dp[i][j] <= dp[i][j-1] + 1
        //
        // charAt(i)!=chatAt(j) 时
        // 状态转移方程 dp[i][j] = Math.min(dp[i-1][j]+1,dp[i-1][j-1]+1,dp[i][j-1]+1);
        // 相同时
        // dp[i][j] = Math.min(dp[i-1][j]+1,dp[i-1][j-1],dp[i][j-1]+1);
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int left_down = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                dp[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }


        return dp[m][n];
        //horse ros
        //移除h  ors
        //移除r  os
        //新增r  ros


    }
}
