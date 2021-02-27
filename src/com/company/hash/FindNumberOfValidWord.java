package com.company.hash;

import com.company.dp.Word;
import javafx.util.Pair;

import java.util.*;

public class FindNumberOfValidWord {
    public static void main(String[] args) {

//        String word = "word";
//        System.out.println(getMark(word));
//        String puzzle = "wrodxx";
//        System.out.println(getMark(puzzle));
//
//        if ((getMark(puzzle) & getMark(word)) == getMark(word)) {
//            char c = puzzle.charAt(0);
//            int firstMark = getMark(c);
//            int wordMark = getMark(word);
//            if ((firstMark & wordMark) == firstMark) {
//                System.out.println("success");
//            }
//        }

        String[] words = {"apple", "pleas", "please"};
        String[] puzzles = {"aelpsxy", "saelpxy"};
        System.out.println(new FindNumberOfValidWord().findNumOfValidWords2(words, puzzles));

    }

    public List<Integer> findNumOfValidWords3(String[] words, String[] puzzles) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < puzzles.length; i++) {
            String puzzle = puzzles[i];

            char c = puzzle.charAt(0);
            int count = 0;
            for (int i1 = 0; i1 < words.length; i1++) {
                String word = words[i1];
                if (word.contains(c + "")) {
                    // 并且 word中所有元素在puzz中存在
                    for (int i2 = 0; i2 < word.length(); i2++) {
                        if (!puzzle.contains(word.charAt(i2) + "")) {
                            break;
                        }
                        if (i2 == word.length() - 1) {
                            count++;
                        }
                    }
                }
            }
            result.add(count);

        }
        return result;
    }
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        // puzzle 谜面
        // word 谜底

        // word 只包含plzzle第一个字母    word.contains(puzzle.char[0])
        // word中每一个字母都能从 puzzle中找到  puzzle.containAll(word.chatAt)

        List<Set<Character>> wordsSetList = new ArrayList<>();

        for (int a = 0; a < words.length; a++) {
            Set<Character> wordSet = new HashSet<>();
            String word = words[a];
            for (int i = 0; i < word.length(); i++) {
                wordSet.add(word.charAt(i));
            }
            wordsSetList.add(wordSet);
        }


        List<Set<Character>> puzzleSetList = new ArrayList<>();

        for (int i = 0; i < puzzles.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < puzzles[i].length(); j++) {
                set.add(puzzles[i].charAt(j));
            }
            puzzleSetList.add(set);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < puzzles.length; i++) {
            int count = 0;
            String puzzle = puzzles[i];
            for (int j = 0; j < words.length; j++) {
                Set<Character> puzzSet = puzzleSetList.get(i);
                Set<Character> wordSet = wordsSetList.get(j);
                if (wordSet.contains(puzzle.charAt(0))) {
                    boolean flag = true;
                    for (Character character : wordSet) {
                        if (!puzzSet.contains(character)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        count++;
                    }
                }
            }
            result.add(count);

        }

        return result;
    }

    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        // puzzle 谜面
        // word 谜底

        // word 只包含plzzle第一个字母    word.contains(puzzle.char[0])
        // word中每一个字母都能从 puzzle中找到  puzzle.containAll(word.chatAt)

        Map<Integer, Integer> wordMap = new HashMap<>();

        for (int a = 0; a < words.length; a++) {
            int mask = getMark(words[a]);
            if (Integer.bitCount(mask) <= 7) { //包含1的数量不能大于7
                Integer frequent = wordMap.getOrDefault(mask, 0);
                frequent = frequent + 1;
                wordMap.put(mask, frequent);
            }
        }


        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < puzzles.length; i++) {
            int puzzMark = getMark(puzzles[i]);
            int firstMark = getMark(puzzles[i].charAt(0));
            int count = 0;

            for (Map.Entry<Integer, Integer> entry : wordMap.entrySet()) {
                int wordMark = entry.getKey();
                if ((wordMark & puzzMark) == wordMark) {
                    if ((firstMark & wordMark) == firstMark) {
                        count += entry.getValue();
                    }
                }
            }
            result.add(count);
        }

        return result;
    }

    public static Integer getMark(String s) {
        int mark = 0;
        for (int i = 0; i < s.length(); i++) {
            mark |= 1 << s.charAt(i) - 'a';
        }
        return mark;
    }

    public static Integer getMark(char a) {
        int mark = 0;
        mark |= 1 << a - 'a';
        return mark;
    }

}
