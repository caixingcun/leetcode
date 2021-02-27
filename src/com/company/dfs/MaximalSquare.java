package com.company.dfs;

import com.sun.rowset.internal.Row;

/**
 * 最大正方形
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxSize = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // 广度搜索 BFS
        // 遍历matrix中每一个元素 如果元素为 1 ，则对其进行bfs 返回最大正方形边长
        int rn = matrix.length;
        int cn = matrix[0].length;
        for (int r = 0; r < rn; r++) {
            for (int c = 0; c < cn; c++) {
                if (matrix[r][r] == '1') { //遇到第一个1 作为正方形左上角
                    maxSize = Math.max(maxSize, 1);
                    int currentMaxSize = Math.min(rn - r, cn - c);
                    for (int k = 0; k < currentMaxSize; k++) {
                        boolean flag = true;
                        //对角线 为0 直接break
                        if (matrix[r + k][c + k] == '0') {
                            break;
                        }
                        //新增的 一行一列是否为 0 break
                        for (int m = 0; m < k; m++) {
                            if (matrix[r + k][c + m] == '0' || matrix[r + m][c + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSize = Math.max(maxSize, k + 1);
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        return maxSize * maxSize;
    }


    public int maximalSquare2(char[][] matrix) {
        // 动态规划
        // 动态规划
        // 元素 1  dp=1
        // 元素 左上 左 上 为1  dp = dp + 1
        // 元素 坐上 左 上 为2  dp = dp + 2
        int rn = matrix.length;
        int cn = matrix[0].length;
        int result = 0;
        int[][] dp = new int[rn][cn];

        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (matrix[i][j] == '1') {
                    if(i==0||j==0){
                        dp[i][j]=(matrix[i][j]=='1'?1:0);
                        result = Math.max(dp[i][j], result);
                    }else{
                        int min = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
                        dp[i][j] = min + 1;
                        result = Math.max(dp[i][j], result);
                    }
                }

            }
        }
        return result*result;
    }

    public int min(int...params){
        int min = params[0];
        for (int i = 1; i < params.length; i++) {
            min = Math.min(params[i], min);
        }
        return min;
    }
}
