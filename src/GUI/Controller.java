package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    ChoiceBox choice;
    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice.getItems().addAll(
                "First-In-First-Serve","Priority","ShortestJobFirst","Round Robin","Multilevel Queue");
    }

    @FXML public void startAll(){
        String selecction = (String) choice.getSelectionModel().getSelectedItem();
        switch (selecction){
            case "First-In-First-Serve":
                break;
            case "Priority":
                break;
            case "ShortestJobFirst":
                break;
            case "Round Robin":
                break;
            case "Multilevel Queue":
                break;
        }
    }
}
