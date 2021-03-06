package com.company.stack;

import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {
//        System.out.println(new DecodeString().decodeString("3[a]2[bc]"));
//        System.out.println(new DecodeString().decodeString("3[a2[c]]"));
//        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
        System.out.println(new DecodeString().decodeString("abc3[cd]xyz"));

//        3[z]2[2[y]pq4[2[jk]e1[f]]]ef
//        3[z]                          zzz
//            2[ 2[y]                   zzz2[ yypq4[jkjkef
//                   pq4[ 2[jk]         ans2 = jkjk
//                              e1[f]   ans3 = ef
//                                 pq4[xxx]   zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef
//         写一个堆栈  遇到右括号 就 出栈 直到有遇到第二个[ 为止  ，搜集出栈内容 进行切割 分离数字  重新排列
    }


    public String decodeString(String s) {
        //堆栈
        //"3[a]2[bc]"
        // 3[a     split [   for.split[0] {append(split[1])}
        // 2[bc
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                needPop(stack);
            } else {
                stack.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        return result.toString();
    }

    private void needPop(Stack<Character> stack) {
//        3[z]2[2[y]pq4[2[jk]e1[f]]]ef
//        3[z]                          zzz
//            2[ 2[y]                   zzz2[ yypq4[jkjkef
//                   pq4[ 2[jk]         ans2 = jkjk
//                              e1[f]   ans3 = ef
//                                 pq4[xxx]   zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef
//         写一个堆栈  遇到右括号 就 出栈 直到有遇到第二个[ 为止  ，搜集出栈内容 进行切割 分离数字  重新排列

        int left_time = 0;
        StringBuilder tempsb = new StringBuilder();
        while (!stack.isEmpty()) {
            if (stack.peek() == '[') {
                left_time++;
            }
            if (left_time < 2) {
                tempsb.insert(0, stack.pop());
            } else { //两次以上 退出循环
                break;
            }
        }

        String[] split = tempsb.toString().split("\\[");
        String leftSplit = split[0];
        StringBuilder head = new StringBuilder();
        while (leftSplit.charAt(0) >= 'a' && leftSplit.charAt(0) <= 'z') {
            head.append(leftSplit.charAt(0));
            leftSplit = leftSplit.substring(1);
        }
        int k = Integer.valueOf(leftSplit);
        StringBuilder sbNew = new StringBuilder();
        if (split.length == 2) {
            for (int i = 0; i < k; i++) {
                sbNew.append(split[1]);
            }
        }
        sbNew.insert(0, head.toString());
        for (int i = 0; i < sbNew.toString().length(); i++) {
            stack.push(sbNew.charAt(i));
        }
    }
}
