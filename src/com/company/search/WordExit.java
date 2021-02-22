package com.company.search;

/**
 * 搜索相关
 */
public class WordExit {

    /**
     * 查询单词是否连续存在在数组中
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     * <p>
     * 字母不能重复使用
     * 贪吃蛇
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        int column = board.length;
        int row = board[0].length;
        //定义网格每个字符被访问状态
        boolean[][] visited = new boolean[column][row];

        //网格中每一个元素 遍历一次， pos 从 0 - word 长度 进行状态判断
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                boolean flag = check(board, visited, word, i, j, 0);
                if (flag) {
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * 检查 s 的第 pos 个元素 是否符合条件 ，并向下一个pos 推移 直到遍历完整个串
     *
     * @param board
     * @param visited
     * @param word
     * @param i
     * @param j
     * @param pos
     * @return
     */
    private boolean check(char[][] board, boolean[][] visited, String word, int i, int j, int pos) {
        //定义出口
        if (board[i][j] != word.charAt(pos)) { // 当前位置数 与 word 的pos 相等
            return false;
        } else if (pos == word.length() - 1) { //并且 索引是目标最后一个pos  ，表明匹配成功
            return true;
        }
        // c1 ==c2 看下一个数据
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        boolean result = false;
        for (int[] direction : directions) {
            //获取下一格的数据
            int newi = i + direction[0];
            int newj = j + direction[1];
            if (newi < 0 || newj < 0 || newi >= board.length || newj >= board[0].length) { //超出边界
                continue;
            }
            if (!visited[newi][newj]) { //下一个位置未被访问  访问
                boolean check = check(board, visited, word, newi, newj, pos + 1); //后面的符合条件
                if (check) { //因为要回溯 修改状态 所以这里不能直接return
                    result = true;
                    break;
                }
            }
        }
        visited[i][j] = false; //回溯  置回状态
        return result;
    }

}



