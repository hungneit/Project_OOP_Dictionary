import main.Dictionary;
import main.DictionaryCommandline;
import main.DictionaryManagement;

public class App {
    public static void main(String[] args) {
        DictionaryCommandline dic = new DictionaryCommandline();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        Dictionary dictionary = new Dictionary();
        String path = "/Users/lehung/Documents/OOP/OOP_Dictionary/DictionaryCommandLine/dictionary.txt";
        dic.dictionaryAdvanced(dictionaryManagement, dictionary, path);
    }
}
