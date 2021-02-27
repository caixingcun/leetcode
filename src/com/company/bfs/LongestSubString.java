package com.company.bfs;

import java.util.HashMap;
import java.util.Map;

/**

 */
public class LongestSubString {
    /**
     *  * 395. 至少有K个重复字符的最长子串
     *  * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     *  * @param s
     *  * @param k
     *  * @return

     */
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        //计数
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Character character : counter.keySet()) {
            if (counter.get(character) < k) {
                int res = 0;
                for (String s1 : s.split(String.valueOf(character))) {
                    res = Math.max(res, longestSubstring(s1, k));
                }
                return res;
            }
        }
        return s.length();
    }
}
