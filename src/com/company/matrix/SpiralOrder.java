package com.company.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralOrder {

    public static void main(String[] args) {

        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(new SpiralOrder().spiralOrder(arr).toString());
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int col = matrix.length;
        int row = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        if (col == 1) {
            for (int i : matrix[0]) {
                result.add(i);
            }
            return result;
        }
        if (row == 1) {
            for (int[] ints : matrix) {
                result.add(ints[0]);
            }
            return result;
        }
        boolean[][] visited = new boolean[col][row];
        int i = 0;
        int j = 0;
        // 00 01 02 12 22 21 20 10 11
        int direct = 1; // 1向右 2向下 3向上 4向右
        while (!visited[i][j]) {
            visited[i][j] = true;
            result.add(matrix[i][j]);
            if (direct == 1) { //向右
                j++;
                if (j >= row || visited[i][j]) { //碰壁向下
                    j--;
                    i++;
                    direct = 2;
                }
            } else if (direct == 2) { //向下
                i++;
                if (i >= col || visited[i][j]) { //碰壁 向左
                    i--;
                    j--;
                    direct = 3;
                }
            } else if (direct == 3) { //向左
                j--;
                if (j < 0 || visited[i][j]) { //碰壁 向上
                    j++;
                    i--;
                    direct = 4;
                }
            } else { //4  向上  碰壁向右
                i--;
                if (i < 0 || visited[i][j]) {
                    i++;
                    j++;
                    direct = 1;
                }
            }
        }
        return result;
    }
}
