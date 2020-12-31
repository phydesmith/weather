package io.javasmithy.controllers;

import io.javasmithy.geo.Geopoint;
import io.javasmithy.geo.GeopointRequester;
import io.javasmithy.weather.ForecastRequester;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private GeopointRequester gpr;
    private ForecastRequester forecastRequester;

    @FXML
    TextField searchField;
    @FXML
    TextArea forecastTextArea;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gpr = new GeopointRequester();
        this.forecastRequester = new ForecastRequester();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void search(){
        Geopoint gp = this.gpr.request(searchField.getText());
        forecastTextArea.setText(
                forecastRequester.request(
                        this.gpr.request(
                                searchField.getText()
                        )
                )
        );
    }

}
