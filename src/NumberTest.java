import javafx.util.Pair;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Level;

public class NumberTest {
    public static void main(String[] args) {
//        int[] arr = {5, 4, 7, 5, 3, 2};
//        int[] arr = {2, 2, 7, 5, 4, 3, 2, 2, 1};
        //           2 3 1 2 2 2 4 5 7


        //           left = 1
        //           right = 5
//                   2 2 7 5 4 3 2 2 1 右侧221重新排序
//                       - - -   - - -
//                   2 2 4 5 7 3 1 2 2
//                   2 3 1 2 2 2 4 5 7

//                   4 2 0 2 3 2 0
//
//        System.out.println(Arrays.toString(arr));
//        nextPermutation(arr);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = new int[]{2, 3, 6, 7};
//        List<List<Integer>> lists = combinationSum(arr, 7);
//        System.out.println(lists);

//        int[] arr = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
//        System.out.println(longestOnes1(arr, 3));


        System.out.println(multiply("9", "9"));
    }

    /**
     * 找出下一个 整数  ，用nums中 数据排列 ，如果没有返回最小的数 升序排列
     * <p>
     * 双循环找出 最右符合条件的 left
     * 根据最符合条件的 left 找出 最小的right
     * 更换 left 与 right 数据
     * left+1 - len-1  重新排序
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null && nums.length <= 1) {
            return;
        }
        // 找出 left 与 right
        // left 要最小 left 最小的情况下 num[right] 找最小的
        int len = nums.length;
        int left = 0;
        int max_left = -1;
        int right = len - 1;
        while (left < right) {
            if (nums[left] < nums[right]) {
                max_left = Math.max(left, max_left);
            }
            left++;
            if (left == right) {
                left = 0;
                right--;
            }
        }
        //找不到更大的数  直接排序即可
        if (max_left == -1) {
            Arrays.sort(nums);
            return;
        }
        // 有 max_left 找出 最小的 nums[right]
        int min_num_index = -1;
        int min_num = 101; //范围 0-100
        right = len - 1;
        while (max_left < right) {
            if (min_num > nums[right] && nums[right] > nums[max_left]) {
                min_num = nums[right];
                min_num_index = right;
            }
            right--;
        }
        //交换两个选中数
        int temp = nums[max_left];
        nums[max_left] = nums[min_num_index];
        nums[min_num_index] = temp;
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums, max_left + 1, len);


    }

    /**
     * 递归回溯（组合优化问题/排序选择问题）
     * <p>
     * <p>
     * 组合总和
     * <p>
     * [A,B,C,D]  target X  index = 0
     * <p>
     * <p>
     * A <= X  [A]          index = 0
     * X = X-A
     * <p>
     * A <= X  [A,A]        index = 0
     * X = X-A
     * ...
     * A ==0  result.add[A,A...]
     * <p>
     * 每次进来                 index = 1
     * [B,C,D]  target
     * <p>
     * 搜索回溯
     *
     * @param candidates
     * @param target
     * @return
     */

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); //定义结果
        List<Integer> combine = new ArrayList<>();  //定义组合
        dfs(candidates, target, result, combine, 0); //从第一个数开始 递归

        return result;
    }


    private static void dfs(int[] candidates, int target, List<List<Integer>> result, List<Integer> combine, int index) {
        //定义出口
        //index 指向超出最后一个数 结束递归
        if (index == candidates.length) {
            return;
        }
        //定义出口  最终target = 0 表示之前的 combine 凑满了
        // 递归到传递进来的target 是 0 标识当前组合 combine 符合条件 将当前组合添加到 result
        if (target == 0) {
            result.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过当前数 下一个 index + 1
        dfs(candidates, target, result, combine, index + 1);
        // 选择当前数
        if (target - candidates[index] >= 0) {  //符合条件
            combine.add(candidates[index]); // 加进combine
            //继续递归  目标值 - 当前值 作为目标值继续递归
            dfs(candidates, target - candidates[index], result, combine, index);
            // 回朔  移除之前 最后一个 加紧 combine 集合的数
            combine.remove(combine.size() - 1); //
        }
    }

    /**
     * Given  A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * Return 6
     * <p>
     * 允许K次翻转 求A经过K次反转后 最长连续1的长度
     *
     * @param A
     * @param K 允许反转的次数
     * @return 思路 双循环 计算出每一个 长度Len 之间的 累加值X  Len-X 表示整个长度中的0的数量
     * Len-X >=3 的最大Len
     */
    public static int longestOnes(int[] A, int K) {
        int len = A.length;
        int xLen = A.length;  //需要寻找的最长长度 从 len - 1

        while (xLen > 0) {
            for (int i = 0; i < len - xLen; i++) {
                int sum = 0;
                for (int j = i; j < i + xLen; j++) { //j 从i - i+xLen-1  保证长度 len
                    sum += A[j];
                }
                if (xLen - sum <= K) { //缺的0 小于K个
                    return xLen;
                }
            }
            xLen--; //长度小一点 再来一遍 看当前长度是否符合条件
        }
        return -1;   //不会走到这里
    }

    /**
     * 双指针 滑动窗口
     *
     * @param A
     * @param K
     * @return
     */
    /**
     * *  超时
     * *  官方示例 思路
     * *   left right 双指针 同时指向 index = 0
     * *   记录right 移动过位置0次数  zero++
     * *   一旦累计数>K ， while循环,直到left-right之间zero<-k  A[left] ==0  zero-- 右移left，
     * *   每次right 右移 并且在上一行 left 右移 救场后， 记录下 left -right 之间的最大距离
     * //    0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1
     * //         ^
     * //           ^
     * *
     */


    public static int longestOnes1(int[] A, int K) {
        int left = 0;
        int right = 0;
        int result = 0;
        int zero = 0;
        //右指针 指向len 终止
        while (right < A.length) {
            if (A[right] == 0) {
                zero++;
                while (zero > K) {
                    if (A[left] == 0) {
                        zero--;
                    }
                    left++;
                }
            }
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    /**
     * 不使用bigInteger实现两个整数相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);


        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len_num1 = num1.length();
        int len_num2 = num2.length();
        int[] result = new int[len_num1 + len_num2 + 1];
        for (int i = 0; i < num1.length(); i++) {
            Integer temp = map.get(num1.charAt(len_num1 - i - 1));
            for (int j = 0; j < num2.length(); j++) {
                int mul = map.get(num2.charAt(len_num2 - j - 1)) * temp;//新数字 0-81
                int index = j + i;
                add(result, index, mul);

            }
        }


        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                flag = true;
            }
            if (flag) {
                sb.append(result[i]);
            }
        }
        return sb.toString();
    }

    private static void add(int[] result, int index, int a) {
        if (result[index] + a >= 10) {
            int more = (a + result[index]) / 10;
            result[index] = (a + result[index]) % 10;

            add(result, index + 1, more);
        } else {
            result[index] = result[index] + a;
        }
    }

}
