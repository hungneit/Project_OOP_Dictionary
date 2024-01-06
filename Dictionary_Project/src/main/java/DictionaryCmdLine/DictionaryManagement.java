package DictionaryCmdLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.*;

public class DictionaryManagement {
//    protected Dictionary dictionary = new Dictionary();
private static DictionaryManagement instance;
    public static DictionaryManagement getInstance() {
        if (instance == null) {
            instance = new DictionaryManagement();
        }
        return instance;
    }
    protected Map<String, Pair<String, Integer>> mp = new HashMap<>();
    private Trie trie = new Trie();

    private IOdata_sql io = new IOdata_sql();
    public String dictionaryLookup(String t) {
        if (!mp.containsKey(t)) {
            return null;
        }
        return mp.get(t).getKey();
    }
    public void insertFromCommandline(Dictionary dictionary) {
        dictionary.addAll(io.insertWord());
        addAllTrie(dictionary);
        for (int i =0; i < dictionary.size(); i++) {
            mp.put(dictionary.get(i).getWord_target(), new Pair(dictionary.get(i).getWord_explain(), i));
        }
    }

    public void dictionaryAdd(Dictionary dictionary, String t, String e) {
        mp.put(t, new Pair(e, dictionary.size()));
        Word w = new Word(t, e);
        dictionary.add(w);
        trie.insert(t);
        io.addWord(t, e);
    }

    public void dictionaryUpdate_replace(Dictionary dictionary, String t, String e) {
        int tmp = mp.get(t).getValue();
        mp.replace(t, new Pair(e, tmp));
        dictionary.get(tmp).setWord_explain(e);
        io.replaceWord(t, e);
    }

    public void dictionaryUpdate_add(Dictionary dictionary, String t, String e) {
        int tmp = mp.get(t).getValue();
        String oldExplain = dictionary.get(tmp).getWord_explain();
        dictionary.get(tmp).setWord_explain( oldExplain + e);
        mp.replace(t, new Pair(oldExplain + e, tmp));

        io.replaceWord(t, oldExplain + e);
    }

    public void dictionaryRemove(Dictionary dictionary, String t) {
        int tmp = mp.get(t).getValue();
        dictionary.remove(dictionary.get(tmp));
        mp.remove(t);
        io.removeWord(t);
    }

    public void addAllTrie(Dictionary dictionary) {
        try {
            for (Word w : dictionary) {
                trie.insert(w.getWord_target());
            }
        } catch (NullPointerException e) {
            System.out.println("Dictionary is blank!");
        }
    }

    public ObservableList<String> dictionarySearcher(Dictionary dictionary, String t) {
        List<String> list = trie.searchWithPrefix(t);
        ObservableList<String> result = FXCollections.observableArrayList();
        for (int i = 0; i < Math.min(20, list.size()); i++) {
            result.add(list.get(i));
        }
        return result;
    }
}

