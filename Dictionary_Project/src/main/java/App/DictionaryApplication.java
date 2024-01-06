package App;

import DictionaryCmdLine.Dictionary;
import DictionaryCmdLine.DictionaryManagement;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.ScrollEvent;
import javafx.stage.StageStyle;

public class DictionaryApplication extends Application {
    private double x;
    private double y;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Dictionary dictionary = Dictionary.getInstance();
        DictionaryManagement dictionaryManagement = DictionaryManagement.getInstance();
        dictionaryManagement.insertFromCommandline(dictionary);

        Parent root = FXMLLoader.load(getClass().getResource("DictionaryGUI.fxml"));
        stage.setTitle("Dictionary Application");
        stage.initStyle(StageStyle.DECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
        Scene scene = new Scene(root);
        scene.setOnScroll(ScrollEvent::consume);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
