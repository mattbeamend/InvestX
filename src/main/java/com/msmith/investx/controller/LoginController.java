package com.msmith.investx.controller;

import com.msmith.investx.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML TextField username;
    @FXML PasswordField password;

    public void onLoginButtonClick() {
        // TODO: Check if username and password are matched to account on database


    }

    public void onSignUpClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(Start.class.getResource(("views/setup-view.fxml")));
        Scene scene = new Scene(loader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }
}
