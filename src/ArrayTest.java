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


        int[] arr = {1, 2, 2, 3, 1};
        System.out.println(findShortestSubArray(arr));
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
     * 697  数组的度 最短连续子数组
     */
    public static int findShortestSubArray(int[] nums) {

        //找出数组中的度  及其元素
        // 找出 子串 ，子串的度与数组相同，并且子串最短
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //获取每个数的数量
        int max = 0;
        List<Integer> levelNums = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            Integer key = entry.getKey();
            if (value == max) {
                levelNums.add(key);
            } else if (value > max) {
                levelNums.clear();
                levelNums.add(key);
                max = value;
            }
        }
        // num   int[0] =left int[1] = right
        Map<Integer, int[]> result = new HashMap<>();

        // 拿到了最高阶的元素
        //双指针  从左往右  从右往左  第一次遇到 highLevelNum 记录下来即可

        for (int i = 0; i < nums.length; i++) {
            int num_left = nums[i];
            if (levelNums.contains(num_left)) {
                int[] ints = result.getOrDefault(num_left, new int[]{nums.length, 0});
                ints[0] = Math.min(ints[0], i);
                result.put(num_left, ints);
            }
        }

        int j = nums.length - 1;
        while (j >= 0) {
            int num = nums[j];
            if (levelNums.contains(num)) {
                int[] ints = result.getOrDefault(num, new int[]{nums.length, 0});
                ints[1] = Math.max(ints[1], j); //保存最右边的第一个出现num的j
                result.put(num, ints);
            }
            j--;
        }

        int minLen = nums.length;
        for (Map.Entry<Integer, int[]> integerEntry : result.entrySet()) {
            int[] value = integerEntry.getValue();
            int left = value[0];
            int right = value[1];
            minLen = Math.min(right - left + 1, minLen);
        }

        return minLen;
    }

}
