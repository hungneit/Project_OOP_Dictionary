package main;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEndOfWord(true);
    }

    public List<String> searchWithPrefix(String prefix) {
        TrieNode node = searchPrefix(prefix);
        List<String> results = new ArrayList<>();
        collectWords(node, prefix, results);
        return results;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    private void collectWords(TrieNode node, String currentWord, List<String> results) {
        if (node == null) {
            return;
        }

        if (node.isEndOfWord()) {
            results.add(currentWord);
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            collectWords(node.get(ch), currentWord + ch, results);
        }
    }

}
