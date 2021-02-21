import java.util.*;

/**
 * 数组相关
 */
public class ArrayTest {

    public static void main(String[] args) {
//        int[] arr = {1, 1, 2, 3};
//        int i = removeDuplicates2(arr);
//        System.out.println(i);

//        int[] arr = {1, 1, 2, 3};
//        int i = removeElement(arr, 1);
//        System.out.println(i);


        int[] arr = {8,2,4,7};
        System.out.println( new ArrayTest().longestSubarray(arr, 4));;
    }

    /**
     * 原地移除目标元素值相等的元素
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    /**
     * 原地删除重复数据 双指针 方案
     */
    public static int removeDuplicates2(int[] nums) {
        //不等 则保存一位到慢指针 快指针正常遍历
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /**
     * 原地删除重复数据 简单
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {

//          0 0 1 1 1 2 2 3 3 4
//          0 1 2 3 4
//distance   1 2

        int index = 0; //比较两数的第一个数的索引
        int distance = 0; //两比较数的距离
        while (index < nums.length) {
            //当前数与下一个数 相等 移动下下个数 到下一个数


            if (index + distance + 1 < nums.length && nums[index] == nums[index + distance + 1]) { //直到搬运到的后一个数 与当前数不等
                distance++;
                if (index + distance + 1 < nums.length) {
                    nums[index + 1] = nums[index + distance + 1]; //搬运 距离需要加上 distance
                }
            } else { //不相等时 也需要搬运
                if (distance > 0 && index + distance + 1 < nums.length) {
                    nums[index + 1] = nums[index + distance + 1]; //搬运 距离需要加上 distance
                }
            }

            index++;
        }

        return nums.length - distance;
    }

    /**
     * 数独判断
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {

        // i 行
        //  j 列
        //   i/3 格子 0-2
        //   j/3 格子 0-2
        HashSet<Character>[] lines = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            lines[i] = set;
        }

        HashSet<Character>[] rows = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            rows[i] = set;
        }
//        0 0-2  0-2
//        1 3-5  0-2
//        2 6-9  0-2
//        3 0-2  3-5
//        4 3-5  3-5
//        5 6-9  3-5
//        6 0-2  6-9
//        7 3-5  6-9
//        8 6-9  6-9


        HashSet<Character>[] bdList = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            bdList[i] = set;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                //行
                if (lines[i].contains(c)) {
                    return false;
                } else {
                    lines[i].add(c);
                }
                //列
                if (rows[j].contains(c)) {
                    return false;
                } else {
                    rows[j].add(c);
                }
                //栅格
                int bdIndex = 0;
                int line = i / 3;
                int row = j / 3;
                bdIndex = line + 3 * row;

                if (bdList[bdIndex].contains(c)) {
                    return false;
                } else {
                    bdList[bdIndex].add(c);
                }
            }
        }
        return true;

    }

    /**
     * 绝对差不超过限制的最长连续子数组
     * <p>
     * 思路 移动窗口 从左向右 双指针
     * 使用一个自排序 结构存储窗口内容
     * 使用TreeMap  自平衡二叉树 从首尾获取到 最大值和最小值
     * <p>
     * right++  每次保存更新数据到 TreeMap
     * TreeMap 最小值跟最大值 差 >limit 则left++ 直到符合条件
     * 保存当前 left right 之间的距离到 maxLen
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (right < len) {
            //存储 出现的数据 出现次数
            //并且实现数据自动排序 （TreeMap 底层 自平衡二叉树）
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            //窗口内最大最小值差 超出限制值
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left])-1); //左侧元素次数-1
                if (map.getOrDefault(nums[left],0) == 0) { //该元素没有了 直接清空
                    map.remove(nums[left]);
                }
                left++;
            }
            //每一次满足条件的移位 记录当前长度
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

    /**
     * 最大子序和
     * Given  -2 1 -3 4 -1 2 1 -7 4
     * Return   4 -1 2 1 result =6
     * 贪心解法  遇到连续和小于0则丢弃 因为再加会越来越小
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            result = Math.max(result, count);
            //遇到小于等于0 连续和 直接丢弃
            if (count <= 0) {
                count = 0;
            }
        }
        return result;
    }

}
