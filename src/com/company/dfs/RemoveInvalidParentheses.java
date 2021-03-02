package com.company.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 301删除需要括号
 * 出入栈确定多余的符号  左/右 还有个数
 * dfs + 回溯 确定多种删除括号情况
 * 去重
 */
public class RemoveInvalidParentheses {

    private char[] charArray;
    private int len;
    private Set<String> validExpressions = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        len = s.length();
        charArray = s.toCharArray();

        // 遍历计算出左右多余括号
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if (charArray[i] == '(') {
                leftRemove++;
            } else if (charArray[i] == ')') {
                // 遇到右括号 没有左括号 该右括号必须移除
                if (leftRemove == 0) {
                    rightRemove++;
                }
                // 遇到右括号 有左括号 左括号抵消
                if (leftRemove > 0) {
                    leftRemove--;
                }
            }
        }

        StringBuilder path = new StringBuilder();
        dfs(0, 0, 0, leftRemove, rightRemove, path);
        return new ArrayList<>(validExpressions);
    }

    private void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        //出口
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        char c = charArray[index];
        //当前左括号  并且遇到的都是左括号  忽略左括号
        if (c == '(' && leftRemove > 0) {
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        //当前遇到 右括号 右括号多余  忽略右括号
        if (c == ')' && rightRemove > 0) {
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }

        path.append(c);
        if (c != '(' && c != ')') { //当前遇到的是字符  index 自增  左右括号数不变 继续下一个字符
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (c == '(') {  //左括号 ，index 自增 左括号数+1
            //左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) { // 右括号 index 自增 右括号数+1
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);


    }

}
