package com.msmith.investx.controller;

import com.msmith.investx.Start;
import com.msmith.investx.controller.utilities.FileUtility;
import com.msmith.investx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML private TextField username;
    @FXML private TextField target;
    @FXML private DatePicker targetDate;
    @FXML private TextField interestRate;
    @FXML private TextField deposit;
    @FXML private TextField interestEarned;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(User.getInstance().getUsername());
        target.setText(String.format("%.2f", User.getInstance().getTarget()));
        targetDate.setValue(User.getInstance().getTargetDate());
        interestRate.setText(String.valueOf(User.getInstance().getInterestRate()));
        deposit.setText(String.format("%.2f", User.getInstance().getDeposit()));
        interestEarned.setText(String.format("%.2f", User.getInstance().getInterest()));
    }

    public void onSaveAccountClick() {
        updateAccountDetails();
    }

    public void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }

    private void updateAccountDetails() {
        User.getInstance().setUsername(username.getText());
        User.getInstance().setDeposit(Double.parseDouble(deposit.getText()));
        User.getInstance().setInterestRate(Double.parseDouble(interestRate.getText()));
        User.getInstance().setTarget(Double.parseDouble(target.getText()));
        User.getInstance().setTargetDate(targetDate.getValue());
        User.getInstance().setCurrent(Double.parseDouble(deposit.getText()));
        User.getInstance().setInterest(Double.parseDouble(interestEarned.getText()));

        FileUtility.updateUserFile();
    }


}
