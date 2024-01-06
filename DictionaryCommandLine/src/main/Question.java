package main;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String content;
    private List<String> choices = new ArrayList<>();
    private String answer;

    public Question(String content, String answer) {
        this.content = content;
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void addChoice(String choice1, String choice2, String choice3, String choice4) {
        choices.add(choice1);
        choices.add(choice2);
        choices.add(choice3);
        choices.add(choice4);
    }
}
