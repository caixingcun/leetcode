package com.company.hash;


import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    /**
     * 多数元素
     * 找出集合中元素 最多的元素 元素要 大于一半
     * 数量统计 +去重  HashMap
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            Integer num = hashMap.getOrDefault(nums[i], 0);
            hashMap.put(nums[i], num + 1);
            if (num + 1 > len / 2) {
                return nums[i];
            }
        }
        return -1;
    }
}
