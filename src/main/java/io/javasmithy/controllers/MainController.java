package io.javasmithy.controllers;


import io.javasmithy.forecast.ForecastPeriod;
import io.javasmithy.geo.Geopoint;
import io.javasmithy.net.Requester;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private Requester requester;

    @FXML
    TextField searchField;
    @FXML
    ListView<ForecastPeriod> forecastListView;
    @FXML
    ImageView weatherImageView;
    @FXML
    Label weatherLongDescriptionLabel;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.requester = new Requester();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void search(){
        Geopoint gp = this.requester.getGeopoint(searchField.getText());
        String forecastPath = this.requester.getMetaData(gp);
        this.forecastListView.setItems(this.requester.getForecastPeriods(forecastPath));
    }
    @FXML
    public void handleForecastListViewClick(MouseEvent mouseEvent){
        ForecastPeriod forecastPeriod = forecastListView.getSelectionModel().getSelectedItems().get(0);
        weatherLongDescriptionLabel.setText(forecastPeriod.getDetailedForecast());
        weatherImageView.setImage(this.requester.getForecastImage(forecastPeriod.getIcon()));
    }



}
