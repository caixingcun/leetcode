package com.company.tree;

import javax.xml.soap.Node;

/**
 * 前缀树
 * 插入 和 查找 hash
 */
public class Trie {

//    private TrieNode root;
//
//    /**
//     * Initialize your data structure here.
//     */
//    public Trie() {
//        root = new TrieNode();
//    }
//
//    /**
//     * Inserts a word into the trie.
//     */
//    public void insert(String word) {
//        TrieNode node = this.root;
//        for (int i = 0; i < word.length(); i++) {
//            char currentChar = word.charAt(i);
//            if (!node.containKey(currentChar)) {
//                node.put(currentChar, new TrieNode());
//            }
//            node = node.get(currentChar);
//        }
//        node.setEnd();
//    }
//
//    /**
//     * Returns if the word is in the trie.
//     */
//    public boolean search(String word) {
//        TrieNode node = searchPrefix(word);
//        return node != null && node.isEnd;
//    }
//
//    private TrieNode searchPrefix(String word) {
//        TrieNode node = root;
//        for (int i = 0; i < word.length(); i++) {
//            char curLetter = word.charAt(i);
//            if (node.containKey(curLetter)) {
//                node = node.get(curLetter);
//            } else {
//                return null;
//            }
//        }
//        return node;
//    }
//
//    /**
//     * Returns if there is any word in the trie that starts with the given prefix.
//     */
//    public boolean startsWith(String prefix) {
//        TrieNode node = searchPrefix(prefix);
//        return node != null && node.isEnd;
//    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));

    }
    // len =  26
    // get
    // put
    // isEnd

    TrieNode root;

    private Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.get(c) == null) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd(true);
    }

    public boolean search(String word) {
        TrieNode node = searchPre(word);
        return node != null && node.isEnd;
    }

    public TrieNode searchPre(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node = node.get(c);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    public boolean startWith(String prefix) {
        TrieNode node = searchPre(prefix);
        return node != null;
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
