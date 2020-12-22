package io.javasmithy;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Weather");
        BorderPane pane = new BorderPane();
        label.setAlignment(Pos.CENTER);
        pane.setCenter(label);
        stage.setScene(new Scene(  pane,800, 800));
        stage.setTitle("Weather App");
        stage.show();
    }

}