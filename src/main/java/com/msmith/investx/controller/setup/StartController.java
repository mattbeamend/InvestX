package com.msmith.investx.controller.setup;

import com.msmith.investx.Start;
import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StartController {

    @FXML
    public void onStartButtonClick() throws Exception {
        FileInputStream file = new FileInputStream(
                "src/main/resources/com/msmith/investx/files/user-information.ser");

        FXMLLoader fxmlLoader;
        if(file.available() == 0)
            fxmlLoader = new FXMLLoader(Start.class.getResource("views/setup-view.fxml"));
        else {
            fetchUserFileData(file);
            fxmlLoader = new FXMLLoader(Start.class.getResource("views/home-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }

    private void fetchUserFileData(FileInputStream file) {
        try {
            ObjectInputStream stream = new ObjectInputStream(file);
            Object fileData = stream.readObject();

            User.getInstance().setUsername(((User) fileData).getUsername());
            User.getInstance().setDeposit(((User) fileData).getDeposit());
            User.getInstance().setInterestRate(((User) fileData).getInterestRate());
            User.getInstance().setTarget(((User) fileData).getTarget());
            User.getInstance().setTargetDate(((User) fileData).getTargetDate());
            User.getInstance().setCurrent(((User) fileData).getCurrent());
            User.getInstance().setStartDate(((User) fileData).getStartDate());

            stream.close();
            file.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}