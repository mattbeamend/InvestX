package com.msmith.investx.controller;

import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private Label username;
    @FXML private Label target;
    @FXML private Label targetDate;
    @FXML private Label interestRate;
    @FXML private Label deposits;
    @FXML private Label interest;
    @FXML private Label total;

    @FXML private Button onTrackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User.getInstance().initialize();
        updateLabels();
        updateInvestValues();
    }

    private void updateLabels() {
        username.setText(User.getInstance().getUsername());
        target.setText("£" + String.format("%.2f", User.getInstance().getTarget()) );
        targetDate.setText("" + User.getInstance().getTargetDate());
        interestRate.setText((User.getInstance().getInterestRate()) + "%");
        deposits.setText("£" + String.format("%.2f", User.getInstance().getDeposit()));
        interest.setText("£" + String.format("%.2f", User.getInstance().getInterest()));
        total.setText("£" + String.format("%.2f", User.getInstance().getCurrent()));
    }

    private void updateInvestValues() {
        onTrackButton.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdds()));
    }






}
