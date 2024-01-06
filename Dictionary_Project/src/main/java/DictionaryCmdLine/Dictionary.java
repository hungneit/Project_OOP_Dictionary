package DictionaryCmdLine;

import java.util.ArrayList;

public class Dictionary extends ArrayList<Word> {
//    public List<Word> list = new ArrayList<>();
    private static Dictionary instance;

    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }
}
