package com.company.slidewindow;

public class LongestSubString {
    /**
     * 395. 至少有K个重复字符的最长子串
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int ret = 0;
        int n = s.length();
        for (int t = 0; t <= 26; t++) {
            // left
            int l = 0;
            //right
            int r = 0;
            // 字符频数统计
            int[] cnt = new int[26];
            //最大值
            int tot = 0; //字符总数
            int less = 0;
            // less 当前出现字符<k的数量
           // cnt 字符频数
            // tot 字符总类数
            while (r < n) {
                if (cnt[s.charAt(r)-'a']==1) { //当前 right 字符 存在
                    tot++; //字符累加
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    less--;
                }
                while (tot > t) { //
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        tot--;
                        less--;
                    }
                    l++;
                }
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }
}
