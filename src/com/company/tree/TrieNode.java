package com.company.tree;

/**
 * 前缀树节点
 */
public  class TrieNode {
    TrieNode[] root;
    int R = 26;
    boolean isEnd = false;

    public TrieNode() {
        root = new TrieNode[26];
    }

    public TrieNode get(char c) {
        int index = c - 'a';
        return root[index];
    }

    public void put(char c, TrieNode node) {
        int index = c - 'a';
        root[index] = node;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
