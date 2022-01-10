package com.msmith.investx.controller;

import com.msmith.investx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        deposits1.setText("£" + String.format("%.2f", User.getInstance().getDeposit()));
        onTrackButton.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[0]));
        aheadTrack.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[1]));
        behindTrack.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[2]));
    }


    public void onTrackClick() {
        double deposit = User.getInstance().getDeposit();
        deposit = deposit + User.getInstance().getMonthlyAdditions()[0];
        User.getInstance().setDeposit(deposit);
        double current = User.getInstance().getCurrent();
        current = current + User.getInstance().getMonthlyAdditions()[1];
        User.getInstance().setCurrent(current);
        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
        updateInvestValues();
        updateSerialObjectFile();
    }

    public void onAheadTrackClick() {
        double deposit = User.getInstance().getDeposit();
        deposit = deposit + User.getInstance().getMonthlyAdditions()[1];
        User.getInstance().setDeposit(deposit);
        double current = User.getInstance().getCurrent();
        current = current + User.getInstance().getMonthlyAdditions()[1];
        User.getInstance().setCurrent(current);

        LocalDate targetDate = User.getInstance().getTargetDate();
        User.getInstance().setTargetDate(targetDate.minusMonths(12));
        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
        updateInvestValues();
        updateSerialObjectFile();
    }

    public void onBehindTrackClick() {
        double deposit = User.getInstance().getDeposit();
        deposit = deposit + User.getInstance().getMonthlyAdditions()[2];
        User.getInstance().setDeposit(deposit);
        double current = User.getInstance().getCurrent();
        current = current + User.getInstance().getMonthlyAdditions()[1];
        User.getInstance().setCurrent(current);

        LocalDate targetDate = User.getInstance().getTargetDate();
        User.getInstance().setTargetDate(targetDate.plusMonths(12));
        User.getInstance().calculateSuggestedInvestmentValues();
        updateLabels();
        updateInvestValues();
        updateSerialObjectFile();
    }

    private void updateSerialObjectFile() {
        try {
            FileOutputStream file = new FileOutputStream("src/main/resources/com/msmith/investx/files/user-information.ser");
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(User.getInstance());
            stream.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
