package com.company.fib;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契常规做法
 *
 * 递归
 * 时间复杂度 O（2^n）
 *
 *                      6
 *              5               4
 *         4        3        3      2
 *      3     2   2    1   2    1  1  0
 *   2    1
 * 1  0
 *
 * 记忆化搜索
 *
 * 回溯优化 斐波那契数计算
 *
 * 使用 HASH  空间换时间
 * 每次计算 已存在 fib数据直接返回
 *
 *                      6
 *              5               x
 *         4        x        x      x
 *      3     x   x    1   x    1  1  0
 *   2    1
 * 1  0
 *
 * 时间复杂度O(n)
 * 空间复杂度 O(n)
 * 缺点：HASH 有时候存在 key值冲突
 *      HASH 查询时间复杂度不是O(1) 是O(logN)
 */


public class Fibonacci2 {

    private  Map<Integer, Integer> map = new HashMap<>();

    private int fib(int n){
        return dsf(n);
    }

    private  int dsf(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        if (map.containsKey(n)) {  //计算前 进行O(1)查询 看是否存在已经计算好的数据  实际上HASH 树状查询 时间复杂度 O(logN)
            return map.get(n);
        }
        int leftFib = dsf(n - 1);
        int rightFib = dsf(n - 2);
        map.put(n, leftFib + rightFib);
        return leftFib + rightFib;
    }
}
