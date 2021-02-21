package com.company.fib;

import java.util.*;

/**
 * 斐波那契数 继HASH空间换时间后继续优化
 *
 * 使用数组 替代 HASH 用index 替代HASH的key  时间复杂度 真正O(1) 并且不存在key值冲突
 */
public class Fibonacci3 {

    private static int[] memory;

    public int fib(int n){
        memory = new int[n + 1];
        Arrays.fill(memory, -1);
        return dfs(n);
    }
    /**
     * 记忆化搜索
     *
     * 回溯优化 斐波那契数计算
     * @param n
     */
    private static int dfs(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        if (memory[n] != -1) {
            return memory[n];
        }
        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);
        memory[n] = leftFib + rightFib;
        return leftFib + rightFib;
    }

}
