package com.company.fib;

import java.util.Arrays;

/**
 * 求解斐波那契契数 自底而上 （等同 动态规划 思想）
 *  *                      6
 *  *              5               4
 *  *         4        3        3      2
 *  *      3     2   2    1   2    1  1  0
 *  *   2    1
 *  * 1  0
 *
 *
 * f(1)  f(0) -> f(2) ->f(3) ->f(4) ->f(5) ->f(6)->f(7)
 *               f(1) ->f(2) ->f(3) ->f(4) ->f(5)
 *
 * 求出 f(0)..f(n-1) 就能计算出 f(n)
 *
 * 四步骤
 * 1.定义数组
 * 2.初始化数组元素
 * 3.计算数组元素
 * 4.返回结果
 */
public class Fibonacci5 {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        //1.定义数组
        int[] arr = new int[n + 1];
        //2.初始化数组元素
        arr[0] = 0;
        arr[1] = 1;
        //3.计算数组元素
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        //4.返回结果
        return arr[n];
    }



}
