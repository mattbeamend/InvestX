package com.msmith.investx.controller;

import com.msmith.investx.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class StartController {

    @FXML
    public void onStartButtonClick() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("setup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }
}