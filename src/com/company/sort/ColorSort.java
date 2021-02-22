package com.company.sort;

/**
 * 排序相关
 */
public class ColorSort {

    /**
     * 颜色排序 红 0  白 1 蓝 2
     * 不适用代码库 排序函数
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        //冒泡排序可以解决 但时间复杂读 O(2^n)
        //可以使用冒泡的交换思想，首次将所有 0 置换到 开始位置，第二次将所有1置换到 0之后
        int zero_one_len = 0;
        for (int i = 0; i < nums.length; i++) {
            //置换了所有0到数组最前面
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[zero_one_len];
                nums[zero_one_len] = temp;
                zero_one_len++;
            }
        }
        for (int j = zero_one_len; j < nums.length; j++) {
            if (nums[j] == 1) {
                int temp = nums[j];
                nums[j] = nums[zero_one_len];
                nums[zero_one_len] = temp;
                zero_one_len++;
            }
        }
    }
}
