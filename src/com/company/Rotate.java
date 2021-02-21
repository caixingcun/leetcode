package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组旋转
 */
public class Rotate {
    public static void main(String[] args) {
        int[][] matrix = {
                {5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}
//                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        new Rotate().rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

    }


    /**
     * nxn数组原地旋转 顺时针90度
     * 输入：matrix = [[1,2,3],
     * [4,5,6],
     * [7,8,9]]
     * <p>
     * 输出：           [[7,4,1],
     * [8,5,2],
     * [9,6,3]]
     * <p>
     * 思路  奇数 求出中心点
     * Xc，Yc       (1,1)   (1,1)       (1,1)
     * X   Y        (0,1)  （0，2）      (2,2)
     * 🔺X = Xc-X
     * 1       1          -1
     * 🔺Y = Yc-Y
     * 0       -1          -1
     * X1 = Xc-🔺Y
     * 1-0      1-(-1)     1-(-1)
     * Y1 = Yc+🔺X
     * 1+1      1+1        1+(-1)
     * <p>
     * (0,1)->(2,1)
     * (0,2)->(2,2)
     * (2,2)->(2,0)
     * <p>
     * 计算遍历各个点，计算到中心点X,Y差 ，变换后
     * Y+🔺X ,X+🔺Y
     * =========================================
     * <p>
     * 偶数    中心点 index N-1/2   (0.0)
     * Xc  Yc      1.5，1.5         1.5，1.5
     * 🔺X = Xc-X                   1.5
     * 🔺Y = Yc-Y                        1.5
     * X1 -  Xc-🔺Y                 0
     * Y1 =  Yc+🔺X                      3
     * （0，0）-(0,3)
     *
     * @param matrix
     */

    public void rotate(int[][] matrix) {
        // 原地旋转
        //  a[i][j] = a[j][len-i]
        // i 0 - N/2-1 行 列
        // j 0 - N-1
        // 每次旋转四次
        int N = matrix.length; //二位数组长度=一维数组长度=N
        float centerX = (N - 1) * 0.5f;
        float centerY = centerX;

        for (int i = 0; i < N - 1; i++) {  //行
            for (int j = i; j < N - 1 - i; j++) { //列
                System.out.println(j + "-" + i);
                swap(matrix, i, j, centerX, centerY);
            }
        }
    }

    private void swap(int[][] matrix, int x, int y, float Xc, float Yc) {
        float detX = Xc - x;
        float detY = Yc - y;
        int x4 = (int) (Xc + detY);
        int y4 = (int) (Yc - detX);
        int y2 = (int) (Yc + detX);
        int x2 = (int) (Xc - detY);
        int y3 = (int) (Yc + detY);
        int x3 = (int) (Xc + detX);


        int temp = matrix[x][y];
        matrix[x][y] = matrix[x4][y4];
        matrix[x4][y4] = matrix[x3][y3];
        matrix[x3][y3] = matrix[x2][y2];
        matrix[x2][y2] = temp;

    }
}
