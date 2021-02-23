package com.company.hash;

import java.util.*;

public class HashTest {

    //连续最长序列  原数组不需要连续
    public int longestConsecutive(int[]nums){
        Set<Integer> num_set = new HashSet<>();

        for (int num : nums) { //去重
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {  //遍历
            if (!num_set.contains(num - 1)) {  //当前数 -1 不存在在集合中 再遍历
                                                //如果当前数-1 在集合中 说明 又 num-1 来遍历这一次 跟本次重复了
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);

            }
        }
        return longestStreak;
    }

    public int singleNumber(int[] nums) {
        // hashMap
        // 不需要额外空间 那就冒泡排序
        if (nums.length == 1) {
            return nums[0];
        }

        Arrays.sort(nums);

        for (int i = 0 ;i < nums.length; i++) {
            if (i == 0) {
                if (nums[i + 1]!=nums[i]) {
                    return nums[0];
                }
            }else if (i == nums.length - 1) {
                if (nums[i - 1] != nums[i]) {
                    return nums[i];
                }
            }

            else if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                return nums[i];
            }
        }

        return 0;
    }
}
