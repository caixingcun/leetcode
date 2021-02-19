import org.omg.CORBA.INTERNAL;
import sun.print.SunMinMaxPage;
import sun.security.provider.Sun;

import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TwoPointers {
    /**
     * 双指针类型
     *
     * @param args
     */
    public static void main(String[] args) {

//        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] ints = twoSum(arr, 14);
//        System.out.println(ints[0] + "-" + ints[1]);

//        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int size = remoteDuplicates(arr);
//        System.out.println(size+"");

//        int[] arr = {-4, -1, 0, 3, 10};
//        int[] arrPows = squaringSortArray(arr);
//        for (int arrPow : arrPows) {
//            System.out.println(arrPow);
//        }

//        double result = findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4});
//        System.out.println(result+" result");

//        System.out.println(isPalindrome3(121)+"");

//        String s = convertZstring("PAYPALISHIRING", 3);
//        System.out.println(s);
//        long a = myAtoi(" -42");
//        System.out.println(a + "");

//        boolean math = isMatch("aaabc", "a*b*.c*");


//        System.out.println(getRow(1));
//        System.out.println(getRow(2));
//        System.out.println(getRow(3));
//        System.out.println(getRow(4));

//        int[] a = {-2, 0, 3, -1, 4, 0, 3, 4, 1, 1, 1, -3, -5, 4, 0};
//        System.out.println(threeSum2(a));

        int[] arr = {-3, -2, -5, 3, -4};
        System.out.println(threeSumClosest(arr, -1));
    }


    /**
     * 杨辉三角
     *
     * @param k 第n行
     * @return
     */
    public static List<Integer> getRow(int k) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        k = k + 1;
        for (int n = 1; n <= k; n++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (i == 1 || i == n) {
                    list.add(1);
                    continue;
                } else {
                    List<Integer> pre = map.get(n - 1);
                    list.add((pre.get(i - 2) + pre.get(i - 1)));
                }
            }
            map.put(n, list);
        }
        return map.get(k);
    }


    /**
     * 求两个正序数列 m n 数组的中位数
     * Given  [1,2] [3,4]
     * Return (2 + 2) /2 =2
     * <p>
     * Given [0,0] [0,0]
     * Return  (0 + 0 )/2 =0
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;

        int[] arr = new int[length];
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        int right = 0;
        if (length % 2 == 0) {
            right = length / 2;
        } else {
            right = length / 2;
        }

        while (index <= right) {
            if (index1 == length1) {
                arr[index++] = nums2[index2];
                index2++;
            } else if (index2 == length2) {
                arr[index++] = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2]) {
                arr[index++] = nums1[index1];
                index1++;
            } else {
                arr[index++] = nums2[index2];
                index2++;
            }
        }

        if (length % 2 == 1) { //奇数
            return arr[right];
        } else {
            return (arr[right] + arr[right - 1]) / 2d;
        }

    }

    /**
     * two point 双指针问题
     * 需要比较数组中每个元素与其他元素关系时
     * 降低时间空间复杂度
     * 一般链表或者数组 排序好的 找一些组合满足某种限制条件
     * 这种组合可能是一对数 三个数 或者一个子数组
     */
    /**
     * pair with target sum
     * Two Sum
     * 给定数组 跟一个目标target ，要求找出数组中两个和 等于target的数，返回这两个数的 索引
     */
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                return new int[]{map.get(num), i};
            }
            map.put(nums[i], i);
        }
        throw new RuntimeException("no answer");
    }

    /**
     * Remove Duplicates
     * 给定一个有序数组 原地删除其中重复内容 ，使得每个元素只出现一次
     * 不要另外定义一个数组，必须通过O(1) 原地修改，返回新数组长度
     */

    private static int remoteDuplicates(int[] arr) {
        int i = 0;
        for (int n : arr) {
            if (i == 0 || n > arr[i - 1]) {
                arr[i++] = n;
            }
        }
        return i;
    }

    /**
     * Squaring a Sorted Array
     * 给定按非递减顺序排序的整数数组 返回每个数字平方组成的新数组
     * 两个指针
     *
     * @param arr
     * @return
     */
    private static int[] squaringSortArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        int k = r;
        int[] temp = new int[arr.length];
        while (l <= r) {
            int leftPow = (int) Math.pow(arr[l], 2);
            int rightPow = (int) Math.pow(arr[r], 2);
            if (leftPow < rightPow) {
                temp[k--] = rightPow;
                r--;
            } else {
                temp[k--] = leftPow;
                l++;
            }

        }
        return temp;
    }

    /**
     * 判断回文数
     */
    private static boolean isPalindrome(String s) {
        int length = s.length();
        if (length == 1) {
            return true;
        }
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) == s.charAt(s.length() - i - 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;

    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        int size = (x + "").length();

        while (size > 1) {
            int top = (int) (x / Math.pow(10, size - 1));
            int bottom = x % 10;

            if (top == bottom) {
                x = (int) ((x % (Math.pow(10, size - 1))) / 10);
                size = size - 2;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        int y = 0;
        int t = x;
        while (t > 0) {
            int a = (t % 10);
            y = (y * 10 + a);
            t = (t / 10);
        }
        return x == y;
    }

    /**
     * z 字 字符串 转换
     * PAYPALISHIRING  行数 3
     * 转换后
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * @param s       给定原始字符串
     * @param numRows 行数
     * @return 返回处理后的整行
     */
    public static String convertZstring(String s, int numRows) {
        //多条线 处理后的 数据存储空间
        StringBuilder[] lines = new StringBuilder[numRows];
        // 每个循环字符长度
        int cycleLen = 2 * numRows - 2;

        if (cycleLen == 0) {
            cycleLen = 1;
        }
        if (s.length() < numRows) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            int cycle_index = i % cycleLen;
            if (cycle_index < numRows) {
                if (lines[cycle_index] == null) {
                    lines[cycle_index] = new StringBuilder();
                }
                lines[cycle_index].append(s.charAt(i));
            } else {
                int linex = numRows - (cycle_index - numRows + 1) - 1;
                if (lines[linex] == null) {
                    lines[linex] = new StringBuilder();
                }

                lines[linex].append(s.charAt(i));
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(lines[i]);
        }
        return result.toString();
    }

    /**
     * 字符串转数字有符号数
     *
     * @param s
     * @return
     */
    public static long myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean symbel = true; //正数

        if (s.startsWith("-")) {
            s = s.substring(1);
            symbel = false;
        } else if (s.startsWith("+")) {
            s = s.substring(1);
            symbel = true;
        } else {
            symbel = true;
        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        int s1_length = s.length();
        while (index < s1_length && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            sb.append(s.charAt(index));
            index++;
        }

        String s2 = sb.toString();

        while (s2.startsWith("0")) {
            s2 = s2.substring(1);
        }

        if (s2.length() == 0) {
            return 0;
        }

        if (symbel && s2.length() > (Integer.MAX_VALUE + "").length()) {
            return Integer.MAX_VALUE;
        }

        if (!symbel && s2.length() > (Math.abs(Integer.MIN_VALUE) + "").length()) {
            return Integer.MIN_VALUE;
        }


        Long aLong = Long.valueOf(s2);

        if (symbel) { //正数
        } else { //复数
            aLong = -aLong;
        }

        while (aLong < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        while (aLong > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return aLong.intValue();
    }


    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //最新匹配到 * 的情况
                if (p.charAt(j - 1) == '*') {
                    //情况1 * = 0 ，*与规则前一个字符 被当成 空 了
                    f[i][j] = f[i][j - 2];
                    //情况2  * 复制了前面一个字符  要求  当前规则 能匹配 字符串 -1 并且  最新字符串 = *前面的规则字符串
                    //情况一不匹配时再匹配情况2
                    if ((!f[i][j])) { //当前字符 等于规则前一个字符 由于规则是 * ,那就看当前规则能否匹配之前的字符串
                        if (math(s, p, i, j - 1)) {
                            f[i][j] = f[i - 1][j];
                        }
                    }

                } else {
                    //匹配到的最新的 不是 *
                    //按常规匹配规则即可
                    if (math(s, p, i, j)) {
                        //当前轮询 字符与规则 能匹配 ，则看 当前字符前面的字符s[i-1] 和 当前规则前面的规则p[i-1] 是否匹配
                        f[i][j] = f[i - 1][j - 1];
                    }//无需else 没赋值，就是 false
                }
            }
        }
        return f[m][n];
    }

    /**
     * 常规匹配规则 非 *  字符与匹配规则字符 规划表 每一格的匹配T/F
     *
     * @param s 当前字符串
     * @param p 当前匹配规则
     * @param i 当前字符串总长度+1 i指向位置 从0-总s.length  0为空格
     * @param j 当前匹配规则长度+1 j指向位置 从0-总p.length
     * @return
     */
    public static boolean math(String s, String p, int i, int j) {
        //i=0 表示字符串 为了动态规划 表格 加的空 前缀
        //该空格与任何规则 是不能匹配的 除了与空 规则前缀  ，f[0][0] 已经赋值过了 不存在 i=0 j =0的情况
        if (i == 0) {
            return false;
        }
        // 规则是 .  肯定匹配
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        //两个元素相等 也匹配
        return s.charAt(i - 1) == p.charAt(j - 1);

    }

    /**
     * 盛最多水的容器
     * 双指针
     */
    public static int maxArea(int[] heights) {
        //木桶效应,取最短
        //双指针

        int maxArea = 0;
        for (int i = 0; i < heights.length - 1; i++) {
            for (int j = i; j < heights.length; j++) {
                maxArea = Math.max(maxArea, Math.min(heights[i], heights[j]) * (j - i));
            }
        }
        return maxArea;
    }

    /**
     * 盛最多水的容器
     * 双指针
     */
    public static int maxArea2(int[] heights) {
        //木桶效应,取最短
        //双指针
        int a = 0;
        int b = heights.length - 1;
        int maxArea = 0;
        while (b > a) {
            int currentArea = 0;
            if (heights[a] >= heights[b]) {
                currentArea = (b - a) * heights[b];
                b--;
            } else {
                currentArea = (b - a) * heights[a];
                a++;
            }
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }
        }
        return maxArea;
    }

    /**
     * 找出所有的不重复三元组 和为0
     * 双指针
     * 排序 ，从左向 右-2 遍历 查询满足条件的数据（双指针）
     * <p>
     * 将三循环问题，转换为 1个确定数 + 双指针 问题
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = twoSum(nums, i + 1, nums.length - 1, nums[i]);
            for (List<Integer> list : lists) {
                list.add(0, nums[i]);
            }
            result.addAll(lists);
        }
        return result;
    }

    public static List<List<Integer>> twoSum(int[] nums, int left, int right, int target) {
        List<List<Integer>> result = new ArrayList<>();
        while (left < right) {
            int distance = 0 - nums[left] - nums[right];
            if (distance == target) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                result.add(list);
                left++;
                right--;
                while (nums[right] == nums[right + 1]) {
                    right--;
                }
                while (nums[left] == nums[left - 1]) {
                    left++;
                }
            } else if (distance > target) {
                left++;
                while (nums[left] == nums[left - 1]) {
                    left++;
                }
            } else {
                right--;
                while (nums[right] == nums[right + 1]) {
                    right--;
                }
            }


        }
        return result;
    }

    /**
     * 返回最接近target的三个数的和
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = 1000;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(min - target)) {
                    min = sum;
                }
                if (sum > target) {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }

            }
        }

        return min;
    }


    /**
     * 找出所有的不重复三元组 和为0
     * 自研
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        //双指针  求出任意两个数的和 sum2
        // 用0 - sum2 ,看是否在集合中存在，并且 索引不为上面的两数
        Map<Integer, List<Integer>> map = new HashMap<>();
        //忽略第一个i j
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> list = map.get(num);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(num, list);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int distance = 0 - nums[i] - nums[j];
                if (map.containsKey(distance)) {
                    List<Integer> indexs = map.get(distance);
                    for (Integer index : indexs) {
                        if (index > j) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[index]);
                            list.sort((o1, o2) -> o1 - o2);
                            if (!contains(result, list)) {
                                result.add(list);
                            }
                        }

                    }
                }
            }
        }


        return result;
    }

    public static boolean contains(List<List<Integer>> parent, List<Integer> child) {
        for (List<Integer> item : parent) {
            if (item.get(0) == child.get(0) && item.get(1) == child.get(1)) { //三数之和为 0  ，两个数相等 ，就是三个数相等
                return true;
            }
        }
        return false;
    }

    /**
     * 找出传入最大连续为1的个数
     * 连续打卡
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                sum +=  1;
                max = Math.max(sum, max);
            }else{
                sum = 0; //遇到0 情况
            }
        }
        return max;
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                sum +=  1;
            }else{//遇到0 情况
                max = Math.max(sum, max);
                sum = 0;
            }
            if (i == nums.length - 1) {
                max = Math.max(sum, max);
            }
        }
        return max;
    }

}

