package com.msmith.investx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {

    private static Stage container;

    @Override
    public void start(Stage stage) throws IOException {
        container = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("views/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        stage.setTitle("Track Invest");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getContainer() {
        return container;
    }
}