package com.msmith.investx.controller.setup;

import com.msmith.investx.Start;
import com.msmith.investx.controller.utilities.FileUtility;
import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StartController {

    @FXML
    public void onStartButtonClick() throws Exception {
        FileInputStream file = new FileInputStream(
                "src/main/resources/com/msmith/investx/files/user-information.ser");

        FXMLLoader fxmlLoader;
        if(file.available() == 0)
            fxmlLoader = new FXMLLoader(Start.class.getResource("views/setup-view.fxml"));
        else {
            FileUtility.updateUserObject();
            fxmlLoader = new FXMLLoader(Start.class.getResource("views/home-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }
}