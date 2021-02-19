import java.util.HashMap;
import java.util.Map;

/**
 * 算法题目
 * 滑动窗口
 */
public class SlideWindow {

    public static void main(String[] args) {
//        int[] arr = {1, -1, 5, -2, 3};
//        int index = maxSubArrayLen(arr, 3);
//        System.out.println(index + "");

//        int maxLongSubString = lengthOfLongestSubstring("abba");
//        int maxLongSubString = lengthOfLongestSubstring("abcabcbb");
//        int maxLongSubString = lengthOfLongestSubstring("bbbbb");
//        System.out.println(maxLongSubString + "");


        String result = longestPalindrome("ababc");
        System.out.println(result);
    }

    /**
     * Maximum Sum Subarray of Size K
     * 最长子串 和为 k  求最长子串长度
     * 方案 滑动窗口类型
     *
     * @param arr
     * @param k
     */
    private static int maxSubArrayLen(int[] arr, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; //求出从index = 0  - i 的每一个和 加入到 map 集合

            if (sum == k) { // 长度 == k  ，i+1 就是最长子串长度了
                max = i + 1;
            } else if (map.containsKey(sum - k)) { //  map.get(sum-k) 如果存在
                max = Math.max(max, i - map.get(sum - k));  // i - map.get(sum-k)  获取到 i到之前位置的长度 ，max() 看是否是已有最长，最长则替换到max
            }

            //map 集合保存的 key是长度值 value 是长度值对应的最小索引
            if (!map.containsKey(sum)) { // 不包含此长度则 保存长度及位置到 map，保证 map同等长度下 对应的i是最小的 ，这样才能在后续循环 保证 i - map.get(sum - k) 更长
                map.put(sum, i);
            }

        }
        return max;
    }

    /**
     * Maximum Sum Subarray of Size K  （变体）
     * 找出数组中 小于等于k的最长子串长度 不存在 返回0
     * 方案 滑动窗口类型
     */

    /**
     * 找出无重复最长子串长度
     * 方案 滑动窗口类型
     * input    abcabcbb
     * output   3
     * <p>
     * input bbbb
     * output 1
     *
     * @param s
     * @return
     */

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) >= left) {
                    left = map.get(c) + 1;
                }
            }
            maxLength = Math.max(i - left + 1, maxLength);
            map.put(c, i);
        }
        return maxLength;
    }

    /**
     * 查找出最长回文子串
     * Given ababc
     * Return aba
     * <p>
     * 解法
     * Si...Sj 表示每一个元素
     * S[i,j]  表示子串
     * P[i][j] 用来存储 S[i,j] 是否是回文
     * L 表示当前遍历回文串长度-1/回文串距离  从 0 - N-1
     * <p>
     * 当 l = j - i = 0  时  P[i][j] = true  因为只有一个字符
     * 当 l = j - i = 1  时  P[i][j] = (Si==Sj) 只有两个字符 首尾相等 即可
     * 当 l = j - i > 1  时  P[i][j] = (Si==Sj)&&P[i+1][j-1]   由于l从小到大 ，遍历此情况时，P[i+1]P[j-1] 已经被赋值
     * <p>
     * 在遍历 赋值P[i][j] 过程中 当其为true并且 原长度 > l+1 时进行更新最长回文子串  S[i,j]
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int N = s.length();
        boolean[][] P = new boolean[N][N];
        String result = "";
        int maxL = 0;
        for (int l = 0; l < N; l++) {
            for (int i = 0; i < N - l; i++) {
                int j = i + l;
                if (l == 0) {
                    P[i][j] = true;
                } else if (l == 1) {
                    P[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    P[i][j] = P[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }

                if (P[i][j] && l + 1 > result.length()) {
                    result = s.substring(i, i + l + 1);  //注意l是距离   距离 0 - N-1 ，实际长度是 距离+1
                }
            }
        }
        return result;
    }

    /**
     * 找出数组中最长公共前缀
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i) {
                    return strs[0].substring(0, i);
                }
                if (c == strs[j].charAt(i)) {
                    continue;
                } else {
                    if (i >= 0) {
                        return strs[0].substring(0, i);
                    }else{
                        return "";
                    }
                }
            }
        }
        return strs[0];

    }
}
