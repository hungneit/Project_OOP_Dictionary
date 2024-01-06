package App;
import DictionaryCmdLine.Dictionary;
import DictionaryCmdLine.DictionaryManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.input.MouseEvent;

public class SearchController implements Initializable {
    private Dictionary dictionary = Dictionary.getInstance();
    private DictionaryManagement dictionaryManagement = DictionaryManagement.getInstance();
    private final String path = "/Users/lehung/Documents/OOP/OOP_Dictionary/Dictionary_Project/src/main/resources/Utils/dictionary.txt";

    ObservableList<String> list = FXCollections.observableArrayList();

    private int firstIndexOfSearchList = 0;

    Alert alert = new Alert(Alert.AlertType.NONE);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        dictionaryManagement.insertFromCommandline(dictionary);
//        dictionaryManagement.addAllTrie(dictionary);
        setListDefault(0);

        searchField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (searchField.getText().isEmpty()) {
//                    cancelBtn.setVisible(false);
                    setListDefault(0);
                } else {
//                    cancelBtn.setVisible(true);
                    handleOnKeyTyped();
                }
            }
        });

//        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                searchField.clear();
//                notAvailableAlert.setVisible(false);
//                cancelBtn.setVisible(false);
//                setListDefault(0);
//            }
//        });

        explainField.setEditable(false);
        saveBtn.setVisible(false);

    }

    @FXML
    private void handleOnKeyTyped() {
        list.clear();
        String searchKey = searchField.getText().trim();
        list = dictionaryManagement.dictionarySearcher(dictionary, searchKey);
        if (list.isEmpty()) {
            setListDefault(firstIndexOfSearchList);
        } else {
            searchList.setItems(list);
//            firstIndexOfSearchList = dictionaryManagement.searchWord(dictionary, list.get(0));
        }
    }


    private void setListDefault(int index) {
        list.clear();
        for (int i = index; i < index + 12; i++) list.add(dictionary.get(i).getWord_target());
        searchList.setItems(list);
        searchedWord.setText(dictionary.get(index).getWord_target());
        explainField.setText(dictionary.get(index).getWord_explain());
    }

    @FXML
    private void handleClickSearchedWord(MouseEvent arg0) {
        String clickedWord = searchList.getSelectionModel().getSelectedItem();
        if (clickedWord != null) {
            searchedWord.setText(clickedWord);
            explainField.setText(dictionaryManagement.dictionaryLookup(clickedWord));
            explainField.setVisible(true);
            explainField.setEditable(false);
        }
    }

     @FXML
    private void handleClickRemoveBtn() {
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(yes, no);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Hãy thật sự chắc chắn nhé");
        alert.setContentText("Bạn chắc chắn muốn xóa từ này?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            dictionaryManagement.dictionaryRemove(dictionary, searchedWord.getText());
            updateAfterRemoving();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Đã xóa thành công!");
            alert.showAndWait();

        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Thao tác đã bị hủy");
            alert.showAndWait();
        }
    }

    private void updateAfterRemoving() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(searchedWord.getText())) {
                list.remove(i);
                break;
            }
        searchList.setItems(list);
        explainField.setVisible(false);
    }

    @FXML
    private void handleClickEditBtn() {
        explainField.setEditable(true);
        saveBtn.setVisible(true);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn có thể chỉnh sửa nghĩa của từ này");
        alert.showAndWait();
    }

    @FXML
    private void handleClickSaveBtn() {
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(yes, no);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Hãy thật sự chắc chắn nhé");
        alert.setContentText("Bạn chắc chắn muốn cập nhật nghĩa của từ này?");
        alert.showAndWait();
        if (alert.getResult() == yes) {
            dictionaryManagement.dictionaryUpdate_replace(dictionary, searchedWord.getText(), explainField.getText());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Cập nhật thành công!");
            alert.showAndWait();
        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Thao tác đã bị hủy");
            alert.showAndWait();
        }
        saveBtn.setVisible(false);
        explainField.setEditable(false);
    }

    @FXML
    private void handleClickSoundBtn() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin16");

        if (voice != null) {
            voice.allocate();
            voice.speak(searchedWord.getText());
        } else {
            System.err.println("Không thể tìm thấy giọng: kevin16");
        }
    }

    @FXML
    private TextField searchField;


    @FXML
    private ListView<String> searchList;

    @FXML
    private TextArea explainField;

    @FXML
    private Label searchedWord;

    @FXML
    private Button soundBtn, editBtn, removeBtn, saveBtn;
}
