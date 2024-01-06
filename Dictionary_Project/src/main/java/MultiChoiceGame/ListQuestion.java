package MultiChoiceGame;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListQuestion extends ArrayList<Question> {
    public void insertFromCommandline(String path) {
        File f = new File(path);
        try {
            List<String> allText = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
            for (int i = 0; i < allText.size(); i+=6) {
                String content = allText.get(i);
                String choice1 = allText.get(i+1);
                String choice2 = allText.get(i+2);
                String choice3 = allText.get(i+3);
                String choice4 = allText.get(i+4);
                String answer = allText.get(i+5);
                Question question = new Question(content, answer);
                question.addChoice(choice1, choice2, choice3, choice4);
                this.add(question);
            }
            Collections.shuffle(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
