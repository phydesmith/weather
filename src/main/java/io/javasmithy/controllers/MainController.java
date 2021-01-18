package io.javasmithy.controllers;


import io.javasmithy.forecast.ForecastPeriod;
import io.javasmithy.geo.Geopoint;
import io.javasmithy.net.Requester;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.T;

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
        addForecastListviewSelectionListener();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void search(){
        Geopoint gp = this.requester.getGeopoint(this.searchField.getText());
        String forecastPath = this.requester.getMetaData(gp);
        this.forecastListView.setItems(this.requester.getForecastPeriods(forecastPath));
    }

    private void addForecastListviewSelectionListener() {
        this.forecastListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends ForecastPeriod> observableValue, ForecastPeriod oldValue, ForecastPeriod newValue) -> {
                    handleForecastListViewItemSelection();
                }
        );
    }
    @FXML
    public void handleForecastListViewItemSelection(){
        ForecastPeriod forecastPeriod = this.forecastListView.getSelectionModel().getSelectedItems().get(0);
        this.weatherLongDescriptionLabel.setText(forecastPeriod.getDetailedForecast());
        this.weatherImageView.setImage(this.requester.getForecastImage(forecastPeriod.getIcon()));
    }


}
