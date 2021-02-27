package com.company.divide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 分治
 */
public class LongestSubString {

    public static void main(String[] args) {
        System.out.println(new LongestSubString().longestSubstring("bbaaacbd", 3));
    }

    /**
     * 395 至少有k个重复字符的最长子串
     *
     * @param s 给定字符
     * @param k 需要找出最长一个子串 满足 子串内所有字符长度都>=k
     * @return 分治 对于任意不满足条件的字符ch ，切割后 目标字符位于切割后的某个片段中 ，因为一旦包含该字符 就会不满足条件
     * dfs 传入 s ,l=0,r = len-1,k，返回 len
     * 遍历 l-r 之间的数据 频率
     * 遍历频率数 <k 取出 ch
     * 遍历 l-r 一旦 遇到每次遇到 ch dfs 传入 l= l ，r= i-1 ，返回 len 更新len最大值
     * <p>
     * ****c****c***  k=2
     * <p>
     * **** **** ***  目标串在分治串当中 继续分治 尾递归 返回最长满足条件字符串长度
     */
    public int longestSubstring(String s, int k) {

        return dfs(s, 0, s.length() - 1, k);

    }

    private int dfs(String s, int left, int right, int k) {
        System.out.println(s.substring(left, right + 1));
        int[] nums = new int[26]; //存储26个字母频率 位置0 等同 a

        for (int i = left; i <= right; i++) {
            int index = s.charAt(i) - 'a';
            nums[index] = nums[index] + 1;
        }

        char split = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }

        if (split == 0) {
            return right - left + 1;
        }

        int ret = 0;
        int temp_left = left;

        for (int i = left; i <= right; i++) {
            int currChar = s.charAt(i);
            if (split == currChar) {
                int len = dfs(s, temp_left, i - 1, k);
                ret = Math.max(len, ret);
                temp_left = i + 1;
            } else if (i == right) { //最后一段
                int len = dfs(s, temp_left, right, k);
                ret = Math.max(len, ret);
                temp_left = i + 1;
            }
        }

        return ret;
    }

}
