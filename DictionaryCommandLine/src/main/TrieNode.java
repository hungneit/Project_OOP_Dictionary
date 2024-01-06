package main;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }

    public boolean containsKey(char ch) {
        return children.containsKey(ch);
    }

    public TrieNode get(char ch) {
        return children.get(ch);
    }

    public void put(char ch, TrieNode node) {
        children.put(ch, node);
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEnd) {
        isEndOfWord = isEnd;
    }
}
