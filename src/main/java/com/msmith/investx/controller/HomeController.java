package com.msmith.investx.controller;

import com.msmith.investx.controller.utilities.FileUtility;
import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    @FXML private Label deposits1;
    @FXML private Button onTrackButton;
    @FXML private Button aheadTrack;
    @FXML private Button behindTrack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User.getInstance().calculateInterestEarned();
        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
    }

    private void updateLabels() {
        username.setText(User.getInstance().getUsername());
        target.setText("£" + String.format("%.2f", User.getInstance().getTarget()));
        targetDate.setText("" + User.getInstance().getTargetDate());
        interestRate.setText((User.getInstance().getInterestRate()) + "%");
        deposits.setText("£" + String.format("%.2f", User.getInstance().getDeposit()));
        interest.setText("£" + String.format("%.2f", User.getInstance().getInterest()));
        total.setText("£" + String.format("%.2f", User.getInstance().getCurrent()));
        deposits1.setText("£" + String.format("%.2f", User.getInstance().getDeposit()));
        onTrackButton.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[0]));
        aheadTrack.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[1]));
        behindTrack.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[2]));

        FileUtility.updateUserFile();
    }

    public void onTrackClick() {
        User.getInstance().setDeposit(User.getInstance().getDeposit() + User.getInstance().getMonthlyAdditions()[0]);
        User.getInstance().setCurrent(User.getInstance().getCurrent() + User.getInstance().getMonthlyAdditions()[0]);

        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
    }

    public void onAheadTrackClick() {
        User.getInstance().setDeposit(User.getInstance().getDeposit() + User.getInstance().getMonthlyAdditions()[1]);
        User.getInstance().setCurrent(User.getInstance().getCurrent() + User.getInstance().getMonthlyAdditions()[1]);

        User.getInstance().setTargetDate(User.getInstance().getTargetDate().minusMonths(12));
        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
    }

    public void onBehindTrackClick() {
        User.getInstance().setDeposit(User.getInstance().getDeposit() + User.getInstance().getMonthlyAdditions()[2]);
        User.getInstance().setCurrent(User.getInstance().getCurrent() + User.getInstance().getMonthlyAdditions()[2]);

        User.getInstance().setTargetDate(User.getInstance().getTargetDate().plusMonths(12));
        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
    }

}
