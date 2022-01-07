package com.msmith.investx.controller.setup;

import com.msmith.investx.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class StartController {

    @FXML
    public void onStartButtonClick() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("views/setup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }

}