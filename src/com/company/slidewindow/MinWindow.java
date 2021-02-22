package com.company.slidewindow;

import com.sun.org.apache.regexp.internal.RE;

import javax.xml.transform.Result;
import java.util.*;

public class MinWindow {

    public static void main(String[] args) {
//        System.out.println(new MinWindow().minWindow("a", "aa"));
        System.out.println(new MinWindow().minWindow("a", "a"));
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC", "ABC"));
//        Map<Character, Integer> map = new HashMap<>();
//        map.put('A', 1);
//        map.put('B', 2);
//        map.put('C', 3);
//        System.out.println(new MinWindow().contains("", map));
    }

    public String minWindow(String s, String t) {
        //移动窗口
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> stringMap = new HashMap<>();
        int left = 0;
        int right = 0;
        stringMap.put(s.charAt(left), stringMap.getOrDefault(s.charAt(left), 0) + 1);
        String result = "";
        while (left <= right && right < s.length()) {  // right 0-12  len=13;
            String currString = s.substring(left, right + 1);
            boolean currContains = contains(stringMap, map);
            if (currContains) {//当前包含
                if (result.length() == 0 || result.length() > currString.length()) {
                    result = currString;
                }
                stringMap.put(s.charAt(left), stringMap.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            } else { //当前不包含
                right++;
                if (right < s.length()) {
                    stringMap.put(s.charAt(right), stringMap.getOrDefault(s.charAt(right), 0) + 1);
                }
            }

        }

        //    包含 缩小范围 left++
        //    不包含 扩大范围 right++
        //   直到 right == len &&  left < right

        return result;

    }

    private boolean contains(Map<Character, Integer> substringMap, Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            Integer value_substring = substringMap.getOrDefault(key, 0);

            if (value_substring >= value) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }


}
