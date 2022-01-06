package com.msmith.investx.controller;

import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private Label username;
    @FXML private Label target;
    @FXML private Label targetDate;
    @FXML private Label interestRate;
    @FXML private Label deposits;
    @FXML private Label interest;
    @FXML private Label total;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText("Username: " + User.getInstance().getUsername());
        target.setText("Target: " + User.getInstance().getTarget());
        targetDate.setText("Target Date: " + User.getInstance().getTargetDate());
        interestRate.setText("Interest Rate: " + User.getInstance().getInterestRate());
        deposits.setText("Deposits: " + User.getInstance().getDeposit());
        interest.setText("Interest Earned: " + User.getInstance().getInterest());
        total.setText("Current Total " + User.getInstance().getCurrent());
    }
}
