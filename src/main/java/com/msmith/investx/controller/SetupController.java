package com.msmith.investx.controller;

import com.msmith.investx.Start;
import com.msmith.investx.controller.utilities.FileUtility;
import com.msmith.investx.model.FundTracker;
import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;

public class SetupController {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField target;
    @FXML private DatePicker targetDate;
    @FXML private TextField interestRate;
    @FXML private TextField deposit;

    @FXML
    public void onCreateAccountClick() throws Exception {
        addUserDetails();
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }

    private void addUserDetails() {

        // TODO: Connect to MySQL Server, add user account to the database



        User.getInstance().setUsername(username.getText());
        User.getInstance().setDeposit(Double.parseDouble(deposit.getText()));
        User.getInstance().setInterestRate(Double.parseDouble(interestRate.getText()));
        User.getInstance().setTarget(Double.parseDouble(target.getText()));
        User.getInstance().setTargetDate(targetDate.getValue());
        User.getInstance().setCurrent(Double.parseDouble(deposit.getText()));
        User.getInstance().setShares(Double.parseDouble(deposit.getText())/FundTracker.getInstance().getPrice());
        User.getInstance().setInterest(0);
        User.getInstance().setStartDate(LocalDate.now());
        User.getInstance().setLastDepositDate(LocalDate.now());

        FileUtility.updateUserFile();
    }

    public void onBackButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }
}
