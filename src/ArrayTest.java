import javafx.beans.binding.When;

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


//        int[] arr = {1, 2, 2, 3, 1};
//        System.out.println(findShortestSubArray(arr));

//        int[] arr = {3, 4, -1, 1};
//        System.out.println(firstMissingPositive(arr));


//        int[] arr = {4, 2, 0, 3, 2, 5};
//        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println(trap(arr));


        int[] arr = {0, 1, 0, 3, 12};
        new ArrayTest().moveZeroes(arr);
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

    /**
     * 找出其中没有出现的正整数
     * 只能遍历一次
     * 不能定义额外空间
     * Given 1,2,0
     * return  3
     * <p>
     * Given 3,4，-1,1
     * return 2
     * <p>
     * Given 7,8,9，11,12
     * return 1
     * <p>
     * 0<=nums.len<=300
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
            max = Math.max(max, nums[i]);
        }

        for (int i = 1; i <= max; i++) {
            if (!map.containsKey(i)) {
                return i;
            }
        }

        return max + 1;
    }

    /**
     * 接雨水   长度 分别为height 的柱子 ，下雨天能接多少雨水
     * <p>
     * Given    4 2 0 3 2 5
     * Return   9
     * <p>
     * Given    0 1 0 2 1 0 1 3 2 1 2 1
     * Return   6
     * <p>
     * 思路  找到最高跟次高的两跟柱子  记录其索引left right ，计算其内部雨水注满容积  height * len - num[left+1].。num[right-1]
     * height = min(left,right)
     * len = right-left-1
     * <p>
     * 数组分裂 0-left  right- len-1
     * 继续计算 直到 left == right
     * <p>
     * <p>
     * 思路2  分层 从 0 -最高
     * 双指针 从两侧向中间
     *
     * @param nums 柱子高度集合
     * @return
     */
    public static int trap(int[] nums) {
        int[] levels = Arrays.copyOf(nums, nums.length);
        Arrays.sort(levels);

        Set<Integer> levelSet = new HashSet<>();
        for (int i = 0; i < levels.length; i++) {
            if (levels[i] > 0) {
                levelSet.add(levels[i]);
            }
        }
        int index_temp = 0;
        levels = new int[levelSet.size()];
        Iterator<Integer> iterator = levelSet.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            levels[index_temp] = next;
            index_temp++;
        }

        int left = 0;
        int right = nums.length - 1;
        int zone = 0;
        int level_index = 0;
        while (left < right) {
            int level = levels[level_index];
            if (nums[left] < level) {
                left++;
                continue;
            }
            if (nums[right] < level) {
                right--;
                continue;
            }

            int last_level = level_index == 0 ? 0 : levels[level_index - 1];
            zone = zone + getLevelZone(nums, last_level, level, left, right);
            level_index++;
        }
        return zone;
    }

    private static int getLevelZone(int[] nums, int last_level, int level, int left, int right) {
        int space = 0;

        for (int i = left + 1; i < right; i++) {

            if (nums[i] < level) {
                if (nums[i] > last_level) {
                    space = space + (level - nums[i]);
                } else {
                    space = space + (level - last_level);
                }
            }
        }

        return space;
    }

    /**
     * 双指针解法
     * 双指针 从两头遍历
     * 哪头低加哪头
     * 每向中间挪动一次，更新自己这一侧 最大值，如果当前值比最大值小 雨水容量 +=  最大值-当前值
     *
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        int max_left = 0, max_right = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= max_left) {
                    max_left = height[left];
                } else {
                    result += (max_left - height[left]);
                }
                left++;
            } else {
                if (height[right] >= max_right) {
                    max_right = height[right];
                } else {
                    result += (max_right - height[right]);
                }
                right--;
            }
        }
        return result;
    }


    public int climbStairs2(int n) {
        if(n==1){
            return 1;
        }
        //1,状态数组
        int[] dp = new int[n];  //存储到达每一步的 的方案数

        //状态方程 f(n) = f(n-1) + f(n-2) //到达最后一步的方案数  之前可能是一步 或者两步到达n的位置
        //2.初始化状态数组
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     *
     * 路径规划
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //路径规划  递归
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(result, temp, n);
        return result.size();
    }

    private void dfs(List<List<Integer>> result, List<Integer> temp, int n) {
        if (n == 0) {
            result.add(temp);
            return;
        }
        if (n == 1) {
            temp.add(1);
            dfs(result, temp, n - 1);
            temp.remove(temp.size() - 1);
        } else {
            temp.add(1);
            dfs(result, temp, n - 1);
            temp.remove(temp.size() - 1);

            temp.add(2);
            dfs(result, temp, n - 2);
            temp.remove(temp.size() - 1);
        }
    }


    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        return nums[len - k];
    }


    public void moveZeroes(int[] nums) {

        // 冒泡 把0 当成最大的数

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i-1; j++) {
                if (nums[j] == 0 && nums[j + 1] != 0) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }

    }

    /**
     * 寻找重复数
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int orDefault = map.getOrDefault(nums[i], 0);
            if (orDefault > 0) {
                return nums[i];
            }else{
                map.put(nums[i],   1);
            }
        }
        return 0;
    }

    public int findDuplicate2(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int abs = Math.abs(nums[i]);
            int index = abs - 1;
            if (nums[index] < 0) {
                return Math.abs(nums[index]);
            }
            nums[index] = -nums[index];
        }
        return 0;
    }
}
