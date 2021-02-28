package com.company.slidewindow;

import java.util.Arrays;
import java.util.TreeMap;

public class MaxSlidingWindow {

    public static void main(String[] args) {
//        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] arr = {7, 2, 4};
//        int[] arr = {1, -1};
        System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindow(arr, 2)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // TreeMap 存储进行排序 和最大值获取
        // 每移动一位 剔除滑动窗口上一位 加入滑动窗口下一位
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        if (k == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        int left = 0;
        int right = 0;
        treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);

        while (right < nums.length) {
            if (right - left + 1 < k) {
                right++;
                treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
            } else {
                //slide window len = k
                result[left] = treeMap.lastKey();
                left++;
                right++;
                if (right < nums.length) {
                    treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
                }
                if (left - 1 >= 0) {
                    treeMap.put(nums[left-1], treeMap.getOrDefault(nums[left - 1], 0) - 1);
                    if (treeMap.getOrDefault(nums[left-1], 0) <= 0) {
                        treeMap.remove(nums[left-1]);
                    }
                }
            }
        }
        return result;
    }
}
