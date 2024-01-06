package App;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DicController implements Initializable {
    @FXML
    private Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;

    @FXML
    private Button exitButton, searchButton, addButton, transButton, gameButton, homeButton;

    @FXML
    private AnchorPane container;

    @FXML
    private ImageView home;

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            setNode(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("AddGUI.fxml");
            }
        });


        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("Search.fxml");
            }
        });


        transButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("Translate.fxml");
            }
        });
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("MultigameGUI.fxml");
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                container.getChildren().add(home);
            }
        });

        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        tooltip4.setShowDelay(Duration.seconds(0.5));
        tooltip5.setShowDelay(Duration.seconds(0.5));
        tooltip6.setShowDelay(Duration.seconds(0.5));

        exitButton.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }
}
