package io.javasmithy.controllers;

import io.javasmithy.net.Requester;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private Requester requester;

    @FXML
    TextField searchField;
    @FXML
    TextArea forecastTextArea;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.requester = new Requester();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void search(){
        forecastTextArea.setText(
                requester.request(
                        this.requester.request(
                                searchField.getText()
                        )
                )
        );
    }

}
