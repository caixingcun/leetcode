import javafx.util.Pair;

import java.util.*;

public class StringTest {
    public static void main(String[] args) {
//        int i = strStr("hello", "ll");
//        System.out.println(i);

//        String s = "barfoothefoobarman";

//        String[] arr = {"foo", "bar"};
//        System.out.println(findSubstring(s, arr));

//        System.out.println(countAndSay(5));
    }

    public static int strStr(String haystack, String needle) {

        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    return i;
                }
            }
        }
        return index;
    }

    /**
     * 找出 子串拼接 后 在主串中出现的位置
     * 子串可以是不同顺序
     * 子串字符长度相同
     *
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        //子串总长度
        int len = words[0].length();
        int size = words.length;
        List<String> list = new ArrayList<>();
        int length = words[0].length();
        for (String word : words) {
            list.add(word);
        }

        for (int i = 0; i < s.length() - len * size + 1; i++) {
            if (includeAllWords(s, i,length, words,list)) {
                result.add(i);
            }
        }
        return result;

    }

    public static boolean includeAllWords(String s, int start,int length, String[]words,List<String>list) {

        for (int i = 0; i < words.length; i++) {
            int  from = start + i * length;
            int to = start + (i + 1) * length;
            String substring = s.substring(from, to);
            if (list.contains(substring)) {
                list.remove(substring);
                continue;
            } else {
                return false;
            }
        }
        if (list.size() == 0) {
            return true;
        }

        return false;

    }

    /**
     * 描述上一个数
     * 1
     * 11
     * 21
     * 1211
     * 111221
     * 312211
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        String[] result = new String[n];
        result[0] = "1";
        for (int i = 1; i < n; i++) {
            result[i] = read(result[i - 1]);
        }

        return result[n - 1];
    }

    private static String read(String s) {
        List<Character> cList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i - 1 >= 0 && cList.get(i - 1) == c) {
                numList.set(i-1, numList.get(i-1) + 1);
            } else {
                cList.add(c);
                numList.add(1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cList.size(); i++) {
            sb.append(numList.get(i));
            sb.append(cList.get(i));
        }

        return sb.toString();
    }

    /**
     * 44 通配符匹配
     *  ? 匹配任何单个字符
     *  * 匹配任意字符串 包含空字符串
     *
     *  Given  aa  a
     *  Return false
     *
     *  Given   aa  *
     *  Return  true
     *
     *  Given  cb  ?b
     *  Return  true
     *
     *  Given adceb a*b
     *  Return true
     *
     *  动态规划？
     *
     *
     * @param s 需要匹配的字符串
     * @param p 正则
     * @return
     */
    public boolean isMatch(String s, String p) {

        return false;
    }

    /**
     * 同元素字符串分组
     * 字符串相同 排列不同的 字符串进行分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String hash = strAnagramsHash(str);
            if (map.containsKey(hash)) {
                List<String> oldList = map.get(hash);
                oldList.add(str);
                map.put(hash, oldList);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(hash, list);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            result.add(value);
        }
        return result;
    }

    /**
     * 用排序后数组作为 字符串hash值
     * @param s1
     * @return
     */
    public String strAnagramsHash(String s1){
        char[] chars = s1.toCharArray();
        Arrays.sort(chars);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

}
