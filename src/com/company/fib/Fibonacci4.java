package com.company.fib;

import java.util.Arrays;

/**
 * 记忆化搜索
 *
 * 斐波那契数 数组 内存换空间 写法优化
 * 将memory 缓存当作参数传递进递归方法
 */
public class Fibonacci4 {


    public int fib(int n) {
        int[] memory = new int[n + 1];
        Arrays.fill(memory, -1);
        return dfs(n, memory);
    }

    /**
     * 记忆化搜索
     * <p>
     * 回溯优化 斐波那契数计算
     *
     * @param n
     */
    private int dfs(int n, int[] memory) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memory[n] != -1) {
            return memory[n];
        }
        int leftFib = dfs(n - 1,memory);
        int rightFib = dfs(n - 2,memory);
        memory[n] = leftFib + rightFib;
        return leftFib + rightFib;
    }

}
