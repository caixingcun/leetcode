package com.company.hash;

import javafx.util.Pair;

import java.util.*;

public class FindNumberOfValidWord {
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
}
