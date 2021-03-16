package com.company.matrix;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * 生成nxn的螺旋矩阵 从 1- n*n
 */
public class GenerateMatrix {

    public static void main(String[] args) {

        for (int[] ints : new GenerateMatrix().generateMatrix(3)) {
            System.out.println(Arrays.toString(ints));
        }

    }
    public int[][] generateMatrix(int n) {
        int[][] directs = {
                {0, 1},//向右
                {1, 0},//向下
                {0, -1},//向左
                {-1, 0}//向上
        };
        int direct_index = 0;
        int[] current_direct = directs[direct_index];
        int[][] result = new int[n][n];
        int k = 1;
        int i = 0;
        int j = 0;
        while (k <= n * n) {
            result[i][j] = k;
            k++;
            int nexti = i + current_direct[0];
            int nextj = j + current_direct[1];
            if (nexti >= n || nexti < 0 || nextj >= n || nextj < 0 || result[nexti][nextj] != 0) {
                direct_index++;
                direct_index = direct_index % 4;
                current_direct = directs[direct_index];
                i = i + current_direct[0];
                j = j + current_direct[1];
            }else{
                i = nexti;
                j = nextj;
            }
        }
        return result;
    }

}
