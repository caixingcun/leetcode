package com.company;

import java.util.*;

public class Main {

    /**
     *  递归方式完成 电话号码字母组合
     * @param args
     */
    public static void main(String[] args) {
        // write your code here
        List<String> strings = letterCombinations("23");
        System.out.println(strings);

    }

    public static List<String> letterCombinations(String digits) {
        Map<Character, char[]> map = new HashMap<>();
        // 2-9
        char[] num_2 = {'a', 'b', 'c'};
        map.put('2', num_2);
        char[] num_3 = {'d', 'e', 'f'};
        map.put('3', num_3);
        char[] num_4 = {'g', 'h', 'i'};
        map.put('4', num_4);
        char[] num_5 = {'j', 'k', 'l'};
        map.put('5', num_5);
        char[] num_6 = {'m', 'n', 'o'};
        map.put('6', num_6);
        char[] num_7 = {'p', 'q', 'r', 's'};
        map.put('7', num_7);
        char[] num_8 = {'t', 'u', 'v'};
        map.put('8', num_8);
        char[] num_9 = {'w', 'x', 'y', 'z'};
        map.put('9', num_9);
        int len = digits.length();

        int index = 0;
        List<char[]> temps = new ArrayList<>();
        while (index < len) {
            char[] chars = map.get(digits.charAt(index));
            temps.add(chars);
            index++;
        }
        List<String> result = new ArrayList<>();
        inputWord(temps, result, 0, new StringBuffer());

        return result;

    }
    //  a b c
    //  a

    /**
     *
     * @param temps  输入数字匹配的 字母集合
     *               [【a,b，c】，【d，e，f】]
     * @param result    最终结果
     *                  [ad, ae, af, bd, be, bf, cd, ce, cf]
     * @param pos   当前指向的位置  0 1
     *
     * @param sb   每一个最终结果的存储变量 如 ab
     *
     */
    private static void inputWord(List<char[]> temps, List<String> result, int pos,StringBuffer sb) {
        //定义出口  只想位置超过 数字长度 将当前 结果存储变量 加进结果集
        if (pos == temps.size()) {
            result.add(sb.toString());
            return;
        }
        // 获取当前 指向的 位置 的 具体字母集合
        char[] chars = temps.get(pos);

        //遍历字母集合 如 [a,b,c]
        for (int i = 0; i < chars.length; i++) {
            // 追加数字
            sb.append(chars[i]);
            // 追加数字后 再移到下一个数字 再调用 inputWord 方法
            inputWord(temps, result, pos + 1, sb); // ad ae af
            // 回溯 剪枝  比如 a 经过上面的pos 变成 ad  如果不剪枝
            sb.deleteCharAt(pos); //保证下一次轮询 a 不在 否则就会 变成ab  回溯的目的是为了下一个 i=1 时的 b 不被前面的a影响  否则就 adef-bdef-cdef了
        }
    }


}
