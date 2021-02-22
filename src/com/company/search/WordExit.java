package com.company.search;

/**
 *  搜索相关
 */
public class WordExit {

    /**
     * 查询单词是否连续存在在数组中
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *
     * 字母不能重复使用
     * 贪吃蛇
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        boolean[][] visited = new boolean[h][w]; //存储每个位置是否访问过

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0); //从word的第0个数字开始
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int pos) {
        if (board[i][j] != s.charAt(pos)) {
            return false;
        } else if (pos == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};   //四个方向 每个方向 坐标增加值
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, pos + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false; //回溯 改回标志位
        return result;
    }


}
