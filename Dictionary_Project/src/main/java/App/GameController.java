package App;

import MultiChoiceGame.ListQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public ListQuestion listQuestion = new ListQuestion();
    public int x = 0;
    public String path = "/Users/lehung/Documents/OOP/OOP_Dictionary/Dictionary_Project/src/main/resources/Utils/question.txt";

    public Button checkBtn, nextBtn;
    public RadioButton choiceA, choiceB, choiceC, choiceD;
    public Label question;

    public Label result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listQuestion.insertFromCommandline(path);
        this.load();
        nextBtn.setVisible(false);
    }

    @FXML
    public void onClickcheckBtn(ActionEvent event) {
        nextBtn.setVisible(true);
        if (listQuestion.get(x).getAnswer().equals("A")) {
            if (choiceA.isSelected()) {
                result.setTextFill(Color.GREEN);
                result.setText("Exactly!");
            } else {
                result.setTextFill(Color.RED);
                result.setText("Wrong!");
            }
        } else if (listQuestion.get(x).getAnswer().equals("B")) {
            if (choiceB.isSelected()) {
                result.setTextFill(Color.GREEN);
                result.setText("Exactly!");
            } else {
                result.setTextFill(Color.RED);
                result.setText("Wrong!");
            }
        }else if (listQuestion.get(x).getAnswer().equals("C")) {
            if (choiceC.isSelected()) {
                result.setTextFill(Color.GREEN);
                result.setText("Exactly!");
            } else {
                result.setTextFill(Color.RED);
                result.setText("Wrong!");
            }
        } else if (listQuestion.get(x).getAnswer().equals("D")) {
            if (choiceD.isSelected()) {
                result.setTextFill(Color.GREEN);
                result.setText("Exactly!");
            } else {
                result.setTextFill(Color.RED);
                result.setText("Wrong!");
            }
        }
    }

    public void load() {
        question.setText(listQuestion.get(x).getContent());
        choiceA.setText(listQuestion.get(x).getChoices().get(0));
        choiceB.setText(listQuestion.get(x).getChoices().get(1));
        choiceC.setText(listQuestion.get(x).getChoices().get(2));
        choiceD.setText(listQuestion.get(x).getChoices().get(3));
    }

    @FXML
    public void onClicknextBtn(ActionEvent event) {
        nextBtn.setVisible(false);
        x++;
        result.setText("");
        choiceA.setSelected(false);
        choiceB.setSelected(false);
        choiceC.setSelected(false);
        choiceD.setSelected(false);
        if (x >= listQuestion.size()) {
            x = 0;
        }
        this.load();
    }
}