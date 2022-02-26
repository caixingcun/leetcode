

import java.util.ArrayList;
import java.util.List;

/**
 * 数独填字游戏
 */
public class ShuDu {

    // 行  每行九个元素 0-9 ，对应元素存在 数组对应[index] true  ，若不存在 则 false
    // 9    9
    // 行   每行的元素是否存在
    boolean[][] line = new boolean[9][9];
    // 列  每列九个元素
    boolean[][] column = new boolean[9][9];
    // 栅格  每个栅格九个元素    // 3  3  9
    // 行3个栅格 列三个栅格 9个元素
    boolean[][][] block = new boolean[3][3][9];
    //空格 行列 位置
    List<int[]> spaces = new ArrayList<>();

    boolean invalid = false;

    public void solveSudoku(char[][] board) {

        //轮训找出 空格及其 行列 位置
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') { //空格
                    spaces.add(new int[]{i, j});
                } else { // 非空格 标识对应 行 列 位置
                    int digit = c - '0' - 1;
                    line[i][digit] = true;
                    column[j][digit] = true;
                    block[i / 3][j / 3][digit] = true;
                }
            }
        }
        //填字  从spaces[0] 开始
        dfs(board, 0);
    }

    /**
     * @param board
     * @param pos
     */
    private void dfs(char[][] board, int pos) {
        //先定义出口
        if (pos == spaces.size()) {
            invalid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0];
        int j = space[1];

        for (int digit = 0; digit < 9&&!invalid; digit++) {  // 0 - 9 并且 未匹配结束
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {  // 行 列 栅格 不存在该digit 数字
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                //回溯
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }





}
