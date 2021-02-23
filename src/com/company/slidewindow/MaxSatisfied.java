package com.company.slidewindow;

import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaxSatisfied {
    public static void main(String[] args) {

//        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
//        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};

        int[] customers = {4, 10, 10};
        int[] grumpy = {1, 1, 0};

        int X = 2;
        System.out.println(new MaxSatisfied().maxSatisfied2(customers, grumpy, X));


    }

    /**
     * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * 输出：16
     * 解释：
     * 书店老板在最后 3 分钟保持冷静。
     * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 1) {
                customers[i] = customers[i] * -1;
            }
        }
        System.out.println(Arrays.toString(customers));
        int max_sum = 0;

        for (int i = 0; i < len - X + 1; i++) {
            int sum = 0;
            for (int j = 0; j < len; j++) {
                if (j >= i && j <= i + X - 1) { //len长度范围
                    sum += Math.abs(customers[j]);
                } else {
                    if (customers[j] > 0) {
                        sum += customers[j];
                    }
                }
            }
            max_sum = Math.max(max_sum, sum);
        }
        return max_sum;
    }
//
//   //滑动窗口
//   窗口大小为3
//   customers = [1,0,1,2,1,1,7,5],
//   grumpy =    [0,1,0,1,0,1,0,1], X = 3

    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        int sum = 0;

        //求连续和 最大
        int left = 0;

        int maxsumMore = 0;
        int sumMore = 0;

        for (int right = 0; right < len; right++) {
            if (grumpy[right] == 0) {
                sum += customers[right];
                customers[right] = 0;
            }

            maxsumMore += customers[right];

            if ((right - left + 1) > X) { //达到范围
                sumMore = sumMore - customers[left];
                left++;
            }


            maxsumMore = Math.max(sumMore, maxsumMore);

        }

        return sum + maxsumMore;
    }

}
