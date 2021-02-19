import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 堆栈概念相关
 */
public class StackTest {

    public static void main(String[] args) {
        int i = longestValidParentheses(")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())");
        System.out.println(i);
    }

    /**
     * 有效括号对数  根据n生成所有有效阔号对数组合
     * GIVE 3
     * ["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        //n对 2n个字符 2^2n种情况  填充满所有字符即可 获得一个数据
        // 再验证获取数据是否有效
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    /**
     * 生成所有情况的递归函数
     *
     * @param current
     * @param pos
     * @param result
     */
    private static void generateAll(char[] current, int pos, List<String> result) {
        //定义出口  在 位置指向 最终尺寸时 添加到结果集
        if (pos == current.length) { //长度满足  最终 pos 从0 -current.length-1   在 = length时被加入到result
            if (valid(current)) { //合法 直接加进result
                result.add(new String(current));
            }
        } else {
            //2*n个字符 每次移动一位 调用两次添加操作 并移动pos 以及传入 result
            current[pos] = '('; //加一个左括号，再调用一次递归 每一次递归 最终会有一个 结果加进result
            generateAll(current, pos + 1, result); // 传递一个组合到 递归方法，传入需要生成的下一个 pos位置
            current[pos] = ')'; // 加一个右括号 ，再调用一次递归 每一次递归 最终会有一个 结果加进result
            generateAll(current, pos + 1, result);
        }
    }
//2, 3, 5, 6,        9, 10,
// 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
// 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
// 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
// 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
// 51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
// 61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
// 71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
// 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
// 91, 92, 93, 94, 95, 96, 97, 98, 99, 100,
// 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
// 111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
// 121, 122, 123, 124, 125, 126, 127, 128, 129, 130,
// 131, 132, 133, 134, 135, 136, 137, 138, 139, 140,
//
// 143, 144, 146, 147, 148, 149, 150,
// 151, 152, 153, 154, 155, 156, 157, 158, 159, 160,
// 161, 162, 163, 164, 165, 166, 167, 168, 169, 170,
// 171, 172, 173, 174, 175, 176, 177, 178, 179, 180,
// 181]

    public static boolean valid(char[] cur) {
        int balance = 0;
        for (char c : cur) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }


    /**
     * 有效地字符串
     * 判断 () [] {} 成对出现
     * <p>
     * 思路 左括号压栈 右括号出栈
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                if (!stack.empty() && stack.pop() == '(') {
                    continue;
                } else {
                    return false;
                }
            }


            if (c == ']') {
                if (!stack.empty() && stack.pop() == '[') {
                    continue;
                } else {
                    return false;
                }
            }

            if (c == '}') {
                if (!stack.empty() && stack.pop() == '{') {
                    continue;
                } else {
                    return false;
                }
            }
        }

        if (!stack.empty()) {
            return false;
        }


        return true;
    }

    /**
     * 最长有效括号 ()  = 2
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        Stack<Integer> leftStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == ')') {
                if (!leftStack.isEmpty()) {
                    Integer index = leftStack.pop();
                    list.add(index);
                    list.add(i);
                }
            }

        }

        if (list.size() == 0) {
            return 0;
        }

        list.sort((o1, o2) -> o1 - o2);
        System.out.println(list);
        int min = list.get(0);
        Integer max = list.get(list.size() - 1);

        int max_size = 0;
        Iterator<Integer> iterator = list.iterator();
        int temp_size = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();

            if (next == min) { //一直相等 可以拿到本段组合的n
                temp_size++;
            } else { // 一旦不相等， next++ 立马记作最小值， n 重置为1
                temp_size = 1;
                min = next;
            }
            min++;

            max_size = Math.max(max_size, temp_size);
        }
        return max_size;
    }

}
